package com.multithread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CreateThread {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CreateThread example = new CreateThread();

        Thread t1 = example.new ExtendedThreadApproach();
        t1.start(); // start the first thread. This calls the run() method.

        Thread t2 = new Thread(example.new RunnableClass());
        t2.start();

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Future<Integer> future = executor.submit(example.new CallableClass());
        System.out.println(future.get());
    }

    class ExtendedThreadApproach extends Thread {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ExtendedThreadApproach run()");
        }
    }

    class RunnableClass implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("RunnableClass run()");
        }
    }

    class CallableClass implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("CallableClass call()");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new Random().nextInt();
        }
    }
}
