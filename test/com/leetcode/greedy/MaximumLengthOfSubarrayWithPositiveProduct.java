package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * 1567. Maximum Length of Subarray With Positive Product
 * 
 * Given an array of integers nums, find the maximum length of a subarray where the product of all
 * its elements is positive.
 * 
 * A subarray of an array is a consecutive sequence of zero or more values taken out of that array.
 * 
 * Return the maximum length of a subarray with positive product.
 * 
 */
public class MaximumLengthOfSubarrayWithPositiveProduct {
    public int getMaxLen(int[] nums) {
        int ans = 0;
        for (int i = 0, j = 0; i < nums.length; i = j) {
            // find next non-zero element£¬ upper bound
            while (j < nums.length && nums[j] != 0) {
                j++;
            }

            int sign = 1;
            int first = j, last = i;
            for (int k = i; k < j; k++) {
                if (nums[k] < 0) {
                    // first negative
                    first = Math.min(first, k);
                    // last negative
                    last = Math.max(last, k);
                    sign *= -1;
                }
            }
            if (sign == 1) {
                ans = Math.max(ans, j - i);
            } else {
                // j - first - 1, we want to exclude both side, so we need to -1
                // last - i, i is valid, exclude one side(last), so no need to -1
                ans = Math.max(ans, Math.max(j - first - 1, last - i));
            }
            // ignore all zero elements
            while (j < nums.length && nums[j] == 0) {
                j++;
            }
        }
        return ans;
    }

    @Test
    public void test0() {
        int[] nums = {1, -2, -3, 4};
        // The array nums already has a positive product of 24.
        assertEquals(4, getMaxLen(nums));
    }

    @Test
    public void test1() {
        int[] nums = {0, -19, 26, -24, -13, -2, 26, 10, 0, 4, 0, -26, -22, 9, 35, -11, -14, 0, -29};
        assertEquals(7, getMaxLen(nums));
    }

}
