package com.hackerank.test;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SimpleTwentyFour {
    // Given an array and judge whether we can find an expression so that its result is 24, the valid operator is + and
    // -
    public boolean find(int[] nums) {
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int i = 0; i < nums.length; ++i) {
            Set<Integer> tmp = new HashSet<>();
            for (int prev : set) {
                tmp.add(prev + nums[i]);
                tmp.add(prev - nums[i]);
            }
            set = tmp;
        }
        return set.contains(24);
    }

    @Test
    public void test0() {
        int[] nums = {2, 48, 12, 14};
        assertTrue(find(nums));
    }
}
