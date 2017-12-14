package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] money = new int[nums.length + 1];
        money[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            money[i] = Math.max(money[i - 2] + nums[i - 1], money[i - 1]);
        }
        return money[money.length - 1];
    }

    public int rob0(int[] nums) {
        int odd = 0, even = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (i % 2 == 0) {
                even = Math.max(odd, even + nums[i]);
            } else {
                odd = Math.max(even, odd + nums[i]);
            }
        }

        return Math.max(odd, even);
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
