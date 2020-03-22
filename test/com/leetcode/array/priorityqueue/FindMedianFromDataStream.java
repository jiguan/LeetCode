package com.leetcode.array.priorityqueue;

import static org.junit.Assert.assertEquals;
import java.util.Collections;
import java.util.PriorityQueue;
import org.junit.Test;

public class FindMedianFromDataStream {


    private static final double DELTA = 1e-15;

    @Test
    public void test0() {
        MedianFinder finder = new MedianFinder();
        finder.addNum(1);
        finder.addNum(2);
        finder.addNum(3);
        finder.addNum(4);
        assertEquals(2.5, finder.findMedian(), DELTA);
    }

    @Test
    public void testPriorityQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        assertEquals(Integer.valueOf(1), queue.poll());
    }

    @Test
    public void test1() {
        MedianFinder finder = new MedianFinder();

        finder.addNum(2);
        finder.addNum(3);
        finder.addNum(4);
        assertEquals(3, finder.findMedian(), DELTA);
    }


    @Test
    public void test3() {
        MedianFinder finder = new MedianFinder();

        finder.addNum(-1);
        finder.addNum(-2);
        finder.addNum(-3);
        assertEquals(-2, finder.findMedian(), DELTA);
    }

}


class MedianFinder {
    // make sure the smallest element is one queue is larger than the largest element in another
    // queue to maintain the sorted order, e.g. max: 3, 2, 1, min: 4, 5
    // big - small
    PriorityQueue<Integer> leftHalf = new PriorityQueue<>(Collections.reverseOrder());
    // small - big
    PriorityQueue<Integer> rightHalf = new PriorityQueue<>();

    // Adds a number into the data structure.
    public void addNum(int num) {
        // leftHalf: 3, 2, 1
        if (leftHalf.isEmpty() || leftHalf.peek() >= num) {
            leftHalf.offer(num);
        } else {
            rightHalf.offer(num);
        }

        // reorganize the heap if needed
        if (rightHalf.size() > leftHalf.size() + 1) {
            leftHalf.offer(rightHalf.poll());
        } else if (leftHalf.size() > rightHalf.size() + 1) {
            rightHalf.offer(leftHalf.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (leftHalf.size() > rightHalf.size()) {
            // large size is always equal or bigger than small
            return leftHalf.peek();
        } else if (rightHalf.size() > leftHalf.size()) {
            return rightHalf.peek();
        } else {
            return (leftHalf.peek() + rightHalf.peek()) / 2.0;
        }
    }
}
