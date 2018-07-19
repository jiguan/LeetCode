package com.multithread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

    public class ThreadSafeArrayList<E> {
        private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        private final Lock readLock = readWriteLock.readLock();
        private final Lock writeLock = readWriteLock.writeLock();
        private final List<E> list = new ArrayList<>();
        public void set(E o) {
            writeLock.lock();
            try {
                list.add(o);
                System.out.println("Adding element by thread " + Thread.currentThread().getName());
            } finally {
                writeLock.unlock();
            }
        }
    
        public E get(int i) {
            readLock.lock();
            try {
                System.out.println("Print element by thread " + Thread.currentThread().getName());
                return list.get(i);
            } finally {
                readLock.unlock();
            }
        }
    
        public void add(E num) {
            List<E> sync = Collections.synchronizedList(list);
            sync.add(num);
        }
    
        public E remove(int i) {
            List<E> sync = Collections.synchronizedList(list);
            if (sync.isEmpty()) return null;
            return sync.remove(i);
        }
    }

class IteratorFailFastTest {

    private ThreadSafeArrayList<Integer> list = new ThreadSafeArrayList<>();

    public IteratorFailFastTest() {
        for (int i = 0; i < 10_000; i++) {
            list.add(i);
        }
    }

    public void runAddThread() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i < 20_000; i++) {
                    System.out.println("Adding " + i);
                    list.add(i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    public void runRemoveThread() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i < 20_000; i++) {
                    System.out.println("Removing " + list.remove(0));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
    }

    public static void main(String[] args) {
        IteratorFailFastTest tester = new IteratorFailFastTest();
        tester.runRemoveThread();
        tester.runAddThread();
    }
}
