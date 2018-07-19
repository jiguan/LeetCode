package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Given an array of numbers and a number k, find the number of subarrays having sum less than k. We may assume that
 * there is no overflow.
 * 
 * Examples :
 * 
 * Input : arr[] = {2, 5, 6} K = 10 Output : 4 The subarrays are {2}, {5}, {6} and {2, 5},
 * 
 * Input : arr[] = {1, 11, 2, 3, 15} K = 10 Output : 4 {1}, {2}, {3} and {2, 3}
 *
 */
public class NumberOfSubarraysHavingSumLessThanK {
    public int countSubarray(int[] nums, int k) {
        int start = 0, end = 0;
        int count = 0, sum = nums[0];
        while (end < nums.length) {
            if (sum < k) {
                end++;
                count += end - start;

                // For last element, end may become len
                if (end < nums.length) {
                    sum += nums[end];
                }
            } else {
                sum -= nums[start];
                start++;
            }
        }
        return count;
    }
    @Test
    public void test0() {
        int[] nums = {2, 5, 6};
        int k = 10;
        assertEquals(4, countSubarray(nums, k));
    }
}
