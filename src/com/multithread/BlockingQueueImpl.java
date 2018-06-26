package com.multithread;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueueImpl {
    public static void main(String[] args) throws Exception {
        BlockingQueue queue = new BlockingQueue();
        Thread producer1 = new Thread(new Producer(queue));
        Thread producer2 = new Thread(new Producer(queue));
        Thread producer3 = new Thread(new Producer(queue));
        Thread consummer = new Thread(new Consummer(queue));
        producer1.start();
        producer2.start();
        producer3.start();
        consummer.start();

        /**
         * Even though there are three producers, 'Queue is empty' happens a lot.
         * 
         * Queue is empty Thread-0 adds 38, queue size: 1 Thread-0 adds 4, queue size: 2 Thread-3 removes 38, queue
         * size: 2 Thread-3 removes 4, queue size: 1 Queue is empty Thread-1 adds 49, queue size: 1 Thread-1 adds 49,
         * queue size: 2 Thread-2 adds 87, queue size: 3 Thread-1 adds 41, queue size: 4 Thread-1 adds 41, queue size: 5
         * Thread-3 removes 49, queue size: 5 Thread-3 removes 49, queue size: 4 Thread-3 removes 87, queue size: 3
         * Thread-3 removes 41, queue size: 2 Thread-3 removes 41, queue size: 1 Queue is empty Thread-0 adds 95, queue
         * size: 1 Thread-3 removes 95, queue size: 1
         * 
         */
    }
}

class BlockingQueue {

    private int limit = 5;
    private long wait = 2000;
    List<Integer> queue = new LinkedList<>();

    public synchronized void enqueue(int num) throws IllegalMonitorStateException, InterruptedException {
        while (queue.size() == limit) {
            System.out.println("Queue is full");
            this.wait();
        }
        queue.add(num);
        System.out.println(Thread.currentThread().getName() + " adds " + num + ", queue size: " + queue.size());
        Thread.sleep(wait);
        this.notifyAll();
    }

    public synchronized int dequeue() throws IllegalMonitorStateException, InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty");
            this.wait();
        }
        System.out.println(
                Thread.currentThread().getName() + " removes " + queue.get(0) + ", queue size: " + queue.size());
        Thread.sleep(wait);
        this.notifyAll(); // notify waiting enqueue threads if any, after exiting this dequeue(), it is ok to compete
        return queue.remove(0);
    }

}

class Consummer implements Runnable {
    private BlockingQueue queue;

    Consummer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.dequeue();
            } catch (IllegalMonitorStateException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer implements Runnable {
    private BlockingQueue queue;

    Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // int num = (int) (Math.random() * 100);
                int num = 1;
                queue.enqueue(num);
            } catch (IllegalMonitorStateException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
