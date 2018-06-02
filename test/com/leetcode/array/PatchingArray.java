package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PatchingArray {
    public int minPatches(int[] nums, int n) {
        // unreachable is the minimum number we cannot reach, [0, unreachable)
        long unreachable = 1;
        int patch = 0, i = 0;
        while (unreachable <= n) {
            if (i < nums.length && nums[i] <= unreachable) {
                unreachable += nums[i++];
            } else {
                unreachable += unreachable;
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
