package com.leetcode.concurrency;

import java.util.concurrent.Semaphore;
import org.junit.Test;

/*
 * Print in Order
 * 
 * The same instance of Foo will be passed to three different threads. Thread A will call first(),
 * thread B will call second(), and thread C will call third(). Design a mechanism and modify the
 * program to ensure that second() is executed after first(), and third() is executed after
 * second().
 * 
 */

public class PrintInOrder {

    private Thread createThread(Foo foo, int index) {
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    switch (index) {
                        case 1:
                            foo.first(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.print("first");
                                }
                            });
                            break;
                        case 2:
                            foo.second(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.print("second");
                                }
                            });
                            break;
                        case 3:
                            foo.third(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.print("third");
                                }
                            });
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Test
    public void test0() throws InterruptedException {
        Foo foo = new Foo();
        int[] input = {1, 2, 3};
        for (int i : input) {
            Thread thread = createThread(foo, i);
            thread.start();
        }
    }

    @Test
    public void test1() throws InterruptedException {
        Foo foo = new Foo();
        int[] input = {1, 3, 2};
        for (int i : input) {
            Thread thread = createThread(foo, i);
            thread.start();
        }
    }
}

class Foo {
    Semaphore run2, run3;
    
    public Foo() {
        run2 = new Semaphore(0);
        run3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        run2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        run2.acquire();
        printSecond.run();
        run3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        run3.acquire();
        printThird.run();
    }
}
