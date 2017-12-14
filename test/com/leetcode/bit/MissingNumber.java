package com.leetcode.bit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        int xor = 0, i = 0;
        for (i = 0; i < nums.length; ++i) {
            xor = xor ^ i ^ nums[i];
        }
        return xor ^ i;
    }

    @Test
    public void test0() {
        // @formatter: off
        // 0, 1, 3 (nums[i])
        //   xor    =>   3 ^ 2
        // 0, 1, 2 (i)
        // @formatter: on
        int[] nums = new int[]{0, 1, 3};
        assertEquals(2, missingNumber(nums));
    }
}
