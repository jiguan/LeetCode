package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PatchingArray {
    public int minPatches(int[] nums, int n) {
        // minSum is smallest sum we want to reach, right now we can build sum in [0, miss)
        long minSum = 1;
        int patch = 0, i = 0;
        while (minSum <= n) {
            if (i < nums.length && nums[i] <= minSum) {
                minSum += nums[i++];
            } else {
                minSum += minSum;
                patch++;
            }
        }
        return patch;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 2};
        int n = 5;
        assertEquals(0, minPatches(nums, n));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 3};
        int n = 6;
        assertEquals(1, minPatches(nums, n));
    }
    
    @Test
    public void test2() {
        int[] nums = new int[]{};
        int n = 8;
        assertEquals(4, minPatches(nums, n));
    }
}
