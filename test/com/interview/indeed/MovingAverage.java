package com.interview.indeed;

import static org.junit.Assert.assertEquals;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import org.junit.Test;

/*
 * Given a stream of input, and a API int getNow() to get the current time stamp, Finish two
 * methods:
 * 
 * 1. void record(int val) to save the record. 2. double getAvg() to calculate the averaged value of
 * all the records in 5 minutes.
 * 
 * Solution: Maintain a sliding window (queue) which stores the elements in 5 minutes. Also maintain
 * the sum of the records in the window.
 * 
 * For the record(), add an event into the queue. Remove all expired events from the queue. For the
 * getAvg(), first remove the expired events from the queue, and then calculate the average.
 * 
 */
public class MovingAverage {
    private Queue<Event> queue = new LinkedList<>();
    private int sum = 0;

    // record an event
    public void record(int val) {
        Event event = new Event(getNow(), val);
        queue.offer(event);
        sum += event.val;

        removeExpiredRecords();
    }

    private int getNow() {
        return 0;
    }

    private void removeExpiredRecords() {
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

    // If getMedian is called frequently, O(nLogn)
    // Heap insert worst O(logn), avg O(1), delete worst O(logn), avg O(logn)
    public double getMedian1() {
        // asec
        PriorityQueue<Integer> upper = new PriorityQueue<>();
        // desc
        PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());

        LinkedList<Event> list = new LinkedList<>(queue);
        for (Event event : list) {
            upper.add(event.time);
            // get the smallest from upper
            lower.add(upper.poll());
            // make sure upper.size >= lower.size
            if (upper.size() < lower.size())
                // get the largest from lower
                upper.add(lower.poll());
        }

        if (upper.size() > lower.size()) {
            return (double) upper.peek();
        } else {
            return (upper.peek() + lower.peek()) / 2.0;
        }
    }

    // If getMedian is called rarely, but insertion is more frequent avg O(n), worst O(n^2)
    // Since we can avoid O(log n) insert time
    public double getMedian2() {
        LinkedList<Event> list = new LinkedList<>(queue);
        int n = list.size();
        int[] arr = new int[n];
        int i = 0;
        for (Event event : list) {
            arr[i++] = event.time;
        }

        if (n % 2 == 0) {
            return (kthSmallest(arr, 0, n - 1, n / 2 - 1) + kthSmallest(arr, 0, n - 1, n / 2)) / 2;
        } else {
            return kthSmallest(arr, 0, n - 1, n / 2);
        }
    }

    // return index
    public int partition(int[] arr, int start, int end) {
        int pivotValue = arr[end];
        int j = start;
        for (int i = start; i < end; i++) {
            // change here to determine asec or desc
            if (arr[i] < pivotValue) {
                swap(arr, i, j);
                j++;
            }
        }
        swap(arr, j, end);
        return j;
    }

    // Implementation of QuickSelect
    private int kthSmallest(int arr[], int left, int right, int k) {
        while (left <= right) {
            // Partition a[left..right] around a pivot
            // and find the position of the pivot
            int pivotIndex = partition(arr, left, right);

            // If pivot itself is the k-th smallest element
            if (pivotIndex == k - 1)
                return arr[pivotIndex];

            // If there are more than k-1 elements on
            // left of pivot, then k-th smallest must be
            // on left side.
            else if (pivotIndex > k - 1)
                right = pivotIndex - 1;

            // Else k-th smallest is on right side.
            else
                left = pivotIndex + 1;
        }
        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    static class Event {
        int time;
        int val;

        public Event(int time, int val) {
            this.time = time;
            this.val = val;
        }
    }

    private double getMedian(int[] arr) {
        int n = arr.length;
        if (n % 2 == 0) {
            int first = kthSmallest(arr, 0, n - 1, n / 2);
            int second = kthSmallest(arr, 0, n - 1, n / 2 + 1);
            return (Double.valueOf(first) + Double.valueOf(second)) / 2;
        } else {
            return kthSmallest(arr, 0, n - 1, n / 2 + 1);
        }
    }

    @Test
    public void test0() {
        int[] arr = {1, 5, 2, 3, 4};
        assertEquals(3, getMedian(arr), 0.001);
    }

    @Test
    public void test1() {
        int[] arr = {1, 5, 2, 3, 4, 6};
        assertEquals(3.5, getMedian(arr), 0.001);
    }

    @Test
    public void test2() {
        int[] arr = {1, 5, 2, 3, 4, 6};
        for(int i = 1; i<=6;++i) {
            assertEquals(Integer.valueOf(i), Integer.valueOf(kthSmallest(arr, 0, arr.length - 1, i)));
        }
    }
}
