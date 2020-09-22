package com.interview.java.basic;

public class MyThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableThread());
        t1.start();

        Thread t2 = new ExtendedThread();
        t2.run();
    }
}


class RunnableThread implements Runnable {
    @Override
    public void run() {}
}


class ExtendedThread extends Thread {
    @Override
    public synchronized void start() {
        super.start();
    }
}
