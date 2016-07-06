package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.PriorityQueue;

import org.junit.Test;

public class FindMedianFromDataStream {
    private static final double DELTA = 1e-15;

    // small's order is from big to small, large is from small to big
    PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder()), large = new PriorityQueue<>();

    // Adds a number into the data structure.
    public void addNum(int num) {
        large.add(num);
        small.add(large.poll());
        if (large.size() < small.size()) {
            // move biggest to large queue
            large.add(small.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (small.size() == large.size()) {
            return (small.peek() + large.peek()) / 2.0;
        } else {
            // large size is always equal or bigger than small
            return large.peek();
        }
    }

    @Test
    public void test0() {
        addNum(2);
        addNum(3);
        assertEquals(2.5, findMedian(), DELTA);
    }


    @Test
    public void test1() {
        addNum(2);
        addNum(3);
        addNum(4);
        assertEquals(3, findMedian(), DELTA);
    }


    @Test
    public void test3() {
        addNum(-1);
        addNum(-2);
        addNum(-3);
        assertEquals(-2, findMedian(), DELTA);
    }

}
