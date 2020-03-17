package com.multithread;

public class ThreadEx extends Thread {
    public void run() {
        for (int loop = 1; loop <= 5; loop++) {
            System.out.print(loop);
        }
    }

    public static void main(String args[]) {
        ThreadEx T1 = new ThreadEx();
        ThreadEx T2 = new ThreadEx();
        T1.start();
        T2.start();
    }
}
