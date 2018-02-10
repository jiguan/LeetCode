package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayNesting {
    public int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            // This value has been visited
            if(nums[i] < 0) continue;
            int len = 1, val = nums[i];
            // Prevent a infinite loop
            while (Math.abs(val) != i) {
                val = nums[Math.abs(val)];
                nums[Math.abs(val)] *= -1;
                len++;
            }
            res = Math.max(res, len);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[] { 5, 4, 0, 3, 1, 6, 2 };
        assertTrue(arrayNesting(nums) == 6);
    }
}
