package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximumSubarray {
    public int maxSubnumsrray(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++)
            nums[0] = Math.max(nums[0], nums[i] = Math.max(nums[i], nums[i] + nums[i - 1]));
        return nums[0];
    }

    @Test
    public void test0() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expected = 6;
        assertEquals(expected, maxSubnumsrray(nums));
    }
}
