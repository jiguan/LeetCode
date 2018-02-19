package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DeleteAndEarn {
    public int deleteAndEarn(int[] nums) {
        int[] sum = new int[10001];
        for (int num : nums) {
            sum[num] += num;
        }

        int[] dp = new int[sum.length];
        dp[1] = sum[1];
        for (int i = 2; i < dp.length; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + sum[i]);
        }
        return dp[dp.length - 1];
    }

    @Test
    public void test0() {
        int[] nums = new int[]{3, 4, 2};
        assertEquals(Integer.valueOf(6), Integer.valueOf(deleteAndEarn(nums)));
    }
}
