package com.leetcode.bit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TotalHammingDistance {
    public int totalHammingDistance(int[] nums) {
        int[] countOnOne = new int[32];
        for (int i = 1; i < 32; ++i) {
            for (int num : nums) {
                countOnOne[i] += (num >> i) & 1;
            }
        }

        int res = 0;
        for (int count : countOnOne) {
            // difference = numbers of 1s * number of 0s
            res += count * (nums.length - count);
        }

        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{4, 14, 2};
        assertEquals(6, totalHammingDistance(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{6, 1, 8, 6, 8};
        assertEquals(22, totalHammingDistance(nums));
    }
}
