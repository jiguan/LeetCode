package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0
 * indexed). Once you pay the cost, you can either climb one or two steps. You
 * need to find minimum cost to reach the top of the floor, and you can either
 * start from the step with index 0, or the step with index 1.
 */
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];

        for (int i = 2; i < dp.length; ++i) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[dp.length - 1];
    }

    @Test
    public void test0() {
        int[] cost = new int[]{10, 15, 20};
        assertEquals(15, minCostClimbingStairs(cost));
    }

    @Test
    public void test1() {
        int[] cost = new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        assertEquals(6, minCostClimbingStairs(cost));
    }
}
