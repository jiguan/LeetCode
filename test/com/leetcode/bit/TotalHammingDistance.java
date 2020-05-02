package com.leetcode.bit;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TotalHammingDistance {
    public int totalHammingDistance(int[] nums) {
        int[] countOnes = new int[32];
        int i = 0;
        while (i < 32) {
            // how many nums are zero only after >> i position
            int zeros = 0;
            for (int num : nums) {
                int tmp = num >> i;
                if (tmp == 0) {
                    zeros++;
                    if (zeros == nums.length) break;
                } else {
                    countOnes[i] += tmp & 1;
                }
            }
            ++i;
        }

        int res = 0;
        for (int j = 0; j < i; ++j) {
            res += countOnes[j] * (nums.length - countOnes[j]);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[] {4, 14, 2};
        assertEquals(6, totalHammingDistance(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[] {6, 1, 8, 6, 8};
        assertEquals(22, totalHammingDistance(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[] {0, 1};
        assertEquals(1, totalHammingDistance(nums));
    }
}
