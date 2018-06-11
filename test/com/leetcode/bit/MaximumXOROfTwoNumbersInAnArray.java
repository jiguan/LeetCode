package com.leetcode.bit;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MaximumXOROfTwoNumbersInAnArray {
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        // must from highest bit, if from lower bit, the max could be misled
        for (int i = 30; i >= 0; --i) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                // reserve left bits
                set.add(num & mask);
            }
            // set bit to 1 since 1 is larger than 0
            int possibleMax = max | (1 << i);
            // find from previous visited num
            for (int prefix : set) {
                // prefix1 ^ prefix2 = possibleMax, then we confirmed this max exists
                if (set.contains(possibleMax ^ prefix)) {
                    max = possibleMax;
                    break;
                }
            }
        }
        return max;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{3, 10, 5, 25, 2, 8};
        int expected = 28;
        assertEquals(expected, findMaximumXOR(nums));
    }

    @Test
    public void test1() {
        // 1110, 1011, 0111, 0010
        int[] nums = new int[]{14, 11, 7, 2};
        int expected = 12;
        assertEquals(expected, findMaximumXOR(nums));
    }

}
