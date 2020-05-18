package com.leetcode.array.divide;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int max = nums[0], sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            max = Math.max(max, sum);
        }
        return max;
    }

    @Test
    public void test0() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expected = 6;
        assertEquals(expected, maxSubArray(nums));
    }
}
