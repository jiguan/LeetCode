package com.interview.indeed;

import java.util.LinkedList;
import java.util.Queue;
import com.interview.indeed.MovingAverage.Event;

public class MovingAverageConcurrancy {

    public static void main(String[] args) throws Exception {
        MovingAverageQueue queue = new MovingAverageQueue();
        Thread producer1 = new Thread(new Feeder(queue));
        Thread producer2 = new Thread(new Feeder(queue));
        Thread producer3 = new Thread(new Feeder(queue));
        Thread consummer = new Thread(new Cleaner(queue));
        producer1.start();
        producer2.start();
        producer3.start();
        consummer.start();
    }
}



class MovingAverageQueue {
    private Queue<Event> queue = new LinkedList<>();
    private int sum = 0;

    // record an event
    public synchronized void record(int val) {
        Event event = new Event(getNow(), val);
        queue.offer(event);
        sum += event.val;

        removeExpiredRecords();
    }

    private int getNow() {
        return 0;
    }

    public synchronized void removeExpiredRecords() {
        while (!queue.isEmpty() && expired(getNow(), queue.peek().time)) {
            Event curr = queue.poll();
            sum -= curr.val;
        }
    }

    public double getAvg() {
        removeExpiredRecords();
        if (queue.isEmpty()) {
            return 0;
        } else {
            return (double) sum / queue.size();
        }
    }

    private boolean expired(int currTime, int prevTime) {
        return currTime - prevTime > 5;
    }
}


class Cleaner implements Runnable {
    private MovingAverageQueue queue;

    Cleaner(MovingAverageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            queue.removeExpiredRecords();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


class Feeder implements Runnable {
    private MovingAverageQueue queue;

    Feeder(MovingAverageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int num = (int) (Math.random() * 100);
            queue.record(num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
