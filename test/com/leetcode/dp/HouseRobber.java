package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HouseRobber {
    public int rob(int[] nums) {
        int yes = 0, no = 0;
        for (int num : nums) {
            int tmp = no;
            // last house not rob, last house robbed
            no = Math.max(no, yes);
            // If decide to rob, num + last house not rob
            yes = num + tmp;
        }
        return Math.max(yes, no);
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 3, 4, 5};
        assertEquals(9, rob(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, 3, 1, 1, 10};
        assertEquals(14, rob(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{1, 2};
        assertEquals(2, rob(nums));
    }

    @Test
    public void test3() {
        int[] nums = new int[]{1};
        assertEquals(1, rob(nums));
    }

    @Test
    public void test4() {
        int[] nums = new int[]{2, 1};
        assertEquals(2, rob(nums));
    }

}
