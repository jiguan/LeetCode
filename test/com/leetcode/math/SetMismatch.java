package com.leetcode.math;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        // num is from 1 to n
        for (int num : nums) {
            if (nums[Math.abs(num) - 1] < 0) {
                res[0] = Math.abs(num);
            } else {
                nums[Math.abs(num) - 1] *= -1;
            }
        }
        // now every element < 0 except the missing one
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] >= 0) {
                res[1] = i + 1;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[] { 1, 2, 2, 4 };
        int[] expected = new int[] { 2, 3 };
        assertTrue(Arrays.equals(expected, findErrorNums(nums)));
    }

    @Test
    public void test1() {
        int[] nums = new int[] { 2, 3, 2 };
        int[] expected = new int[] { 2, 1 };
        assertTrue(Arrays.equals(expected, findErrorNums(nums)));
    }
}
