package com.leetcode.array.slidingwindow;

import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import org.junit.Test;

public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        // Store the index
        Deque<Integer> deque = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < nums.length; ++i) {
            int num = nums[i];
            // if diff is k, need to remove since we are going to add one more later
            while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.pollFirst();
            }
            // if we are about to add is larger than previous added one, then remove previous added
            // small nums
            while (!deque.isEmpty() && nums[deque.peekLast()] < num) {
                deque.pollLast();
            }

            deque.offerLast(i);
            if (i >= k - 1) {
                res[index++] = nums[deque.peek()];
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[] {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] expected = new int[] {3, 3, 5, 5, 6, 7};
        assertTrue(Arrays.equals(expected, maxSlidingWindow(nums, k)));
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1, -1};
        int k = 1;
        int[] expected = new int[] {1, -1};
        assertTrue(Arrays.equals(expected, maxSlidingWindow(nums, k)));
    }

    @Test
    public void test2() {
        int[] nums = new int[] {7, 2, 4};
        int k = 2;
        int[] expected = new int[] {7, 4};
        assertTrue(Arrays.equals(expected, maxSlidingWindow(nums, k)));
    }

    @Test
    public void test3() {
        int[] nums = new int[] {1, 3, 1, 2, 0, 5};
        int k = 3;
        int[] expected = new int[] {3, 3, 2, 5};
        assertTrue(Arrays.equals(expected, maxSlidingWindow(nums, k)));
    }
}
