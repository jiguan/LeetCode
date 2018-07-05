package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int start = -1, end = -2;
        int min = nums[n - 1], max = nums[0];

        for (int i = 1; i < n; ++i) {
            // from left to right, search the current max
            max = Math.max(nums[i], max);
            // from right to left, search the current min
            min = Math.min(nums[n - 1 - i], min);
            // there is an element smaller than max, update start to right so that
            // it could be sorted
            if (nums[i] < max) end = i;
            // there is an element bigger than min, update start to left so that
            // it could be sorted
            if (min < nums[n - 1 - i]) start = n - 1 - i;
        }

        return end - start + 1;
    }

    @Test
    public void test0() {
        int[] nums = new int[] { 2, 6, 4, 8, 10, 9, 15 };
        assertEquals(Integer.valueOf(5), Integer.valueOf(findUnsortedSubarray(nums)));
    }
}
