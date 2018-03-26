package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S > sum || S < -sum) return 0;
        // -sum to sum
        int[] dp = new int[2 * sum + 1];
        dp[sum] = 1;
        for (int i = 0; i < nums.length; ++i) {
            // top-down, take out dp[j] and increase one possibility on next[j + nums[i]] and next[j - nums[i]]
            int[] next = new int[2 * sum + 1];
            for (int j = 0; j < 2 * sum + 1; j++) {
                if (dp[j] != 0) {
                    next[j + nums[i]] += dp[j];
                    next[j - nums[i]] += dp[j];
                }
            }
            dp = next;
        }
        return dp[sum + S];
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int S = 3;
        assertEquals(Integer.valueOf(5), Integer.valueOf(findTargetSumWays(nums, S)));
    }
}
