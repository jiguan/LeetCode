package com.interview.java;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUsage {

    public void submitRunnable() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Runnable");
            }
        });
        executorService.shutdown();
    }

    public void submitCallable() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("Callable");
                return null;
            }
        });
        executorService.shutdown();
    }
}
