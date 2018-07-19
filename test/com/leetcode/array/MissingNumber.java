package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res = nums[i] ^ i ^ res; // a ^ b ^ b = a
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{0, 2, 3, 4, 5, 6};
        assertEquals(1, missingNumber(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{0, 1, 2, 3, 4, 6};
        assertEquals(5, missingNumber(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        assertEquals(0, missingNumber(nums));
    }

    @Test
    public void test3() {
        System.out.println(0 ^ 4);
    }

}
