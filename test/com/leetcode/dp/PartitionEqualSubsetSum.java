package com.leetcode.dp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int half = 0;
        for (int num : nums) {
            half += num;
        }
        if (half % 2 != 0) return false;

        // since we are going to use all nums, the sum of each partition must be sum / 2
        half /= 2;
        boolean[] dp = new boolean[half + 1];
        dp[0] = true;

        // Each num can only be used once, outer loop
        for (int num : nums) {
            // From right to left, to prevent computing later one uses early result
            for (int i = half; i >= num; --i) {
                // don't take num into sum || take current num into sum
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[half];
    }

    // Time Limit exceed
    public boolean canPartition0(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;

        sum /= 2;
        return dfs(nums, sum, 0);
    }

    private boolean dfs(int[] nums, int target, int i) {
        if (i >= nums.length || target < nums[i]) return false;
        if (target == nums[i]) return true;
        return dfs(nums, target - nums[i], i + 1) || dfs(nums, target, i + 1);
    }

    @Test
    public void test0() {
        int[] nums = new int[] { 1, 5, 11, 5 };
        assertTrue(canPartition(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[] { 1, 2, 3, 5 };
        assertFalse(canPartition(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[] { 1, 2, 5 };
        assertFalse(canPartition(nums));
    }

    @Test
    public void test3() {
        int[] nums = new int[] { 2, 2, 3, 5 };
        assertFalse(canPartition(nums));
    }
}
