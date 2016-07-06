package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountOfRangeSum {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length];
        for(int i=0;i<nums.length;i++) {
            sum[i] = (i==0 ? 0 : sum[i-1]) + nums[i];
        }
        return (int) ((upper >= nums.length ? sum[sum.length-1] : sum[upper]) - (lower <= 0 ? 0 : sum[lower-1]));
    }
    
    @Test
    public void test0() {
        int[] nums = new int[]{-2, 5, -1};
        assertEquals(3, countRangeSum(nums, -2, 2));
        assertEquals(-2, countRangeSum(nums, 0, 0));
        assertEquals(-1, countRangeSum(nums, 2, 2));
    }
    
}
