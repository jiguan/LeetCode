package com.leetcode.array.slidingwindow;

import static org.junit.Assert.assertEquals;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;

/*
 * Moving Average from Data Stream
 * 
 * Given a stream of integers and a window size, calculate the moving average of all integers in the
 * sliding window.
 */
public class MovingAverageFromDataStream {

    @Test
    public void test0() {
        MovingAverage m = new MovingAverage(3);
        assertEquals(1, m.next(1), 0.001);
        assertEquals(5.5, m.next(10), 0.001);
        assertEquals(4.6667, m.next(3), 0.001);
        // (10 + 3 + 5) / 3
        assertEquals(6, m.next(5), 0.001);
    }
}


class MovingAverage {

    Queue<Integer> queue = new LinkedList<>();
    int size;
    double sum = 0;

    public MovingAverage(int size) {
        this.size = size;
    }

    public double next(int val) {
        if(queue.size() >= size) {
            sum -= queue.poll();
        }
        sum += val;
        queue.add(val);
        return sum / queue.size();
    }
}
