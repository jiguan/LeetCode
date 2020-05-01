package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        int[] sum = new int[nums.length];
        sum[sum.length - 1] = nums[nums.length - 1];
        for (int i = sum.length - 2; i >= 0; --i) {
            sum[i] = sum[i + 1] + nums[i];
        }

        return dfs(nums, sum, 0, S);
    }

    private int dfs(int[] nums, int[] sum, int index, int S) {
        if (index == nums.length || sum[index] < S) {
            return S == 0 ? 1 : 0;
        }

        return dfs(nums, sum, index + 1, S + nums[index]) + dfs(nums, sum, index + 1, S - nums[index]);
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        int S = 3;
        assertEquals(Integer.valueOf(5), Integer.valueOf(findTargetSumWays(nums, S)));
    }
}
