package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

//http://codercareer.blogspot.com/2011/09/no-03-maximum-sum-of-all-sub-arrays.html
/*
 * A sub-array has one number of some continuous numbers. Given an integer array with positive numbers and negative numbers, get the maximum sum of all sub-arrays. Time complexity should be O(n).
 * For example, in the array {1, -2, 3, 10, -4, 7, 2, -5}, its sub-array {3, 10, -4, 7, 2} has the maximum sum 18.
 */

public class MaximumSumOfAllSubArrays {
    
    public int maxSum(int[] nums) {
        int max = nums[0];
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for(int i=1;i<nums.length;i++) {
            sum[i] = Math.max(nums[i], nums[i] + sum[i-1]);
            if(sum[i] > max) max = sum[i];
        }
        return max;
        
    }
    
    @Test
    public void test0() {
        int[] nums = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
        assertEquals(18, maxSum(nums));
    }
    
    @Test
    public void test1() {
        int[] nums = new int[]{1, -2, -3, -10, -4, 7, 2, -5};
        assertEquals(9, maxSum(nums));
    }

}
