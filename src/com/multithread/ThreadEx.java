package com.multithread;

public class ThreadEx extends Thread {
    private String name;

    public ThreadEx(String name) {
        this.name = name;
    }

    public void run() {
        for (int loop = 1; loop <= 5; loop++) {
            System.out.print(name + " " + loop+", ");
        }
        System.out.println("End");
    }

    public static void main(String args[]) {
        ThreadEx T1 = new ThreadEx("T1");
        ThreadEx T2 = new ThreadEx("T2");
        T1.start();
        T2.start();
    }
}
