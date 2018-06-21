package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GuessNumberHigherOrLowerII {
    public int getMoneyAmount0(int n) {
        int[][] cost = new int[n + 1][n + 1];
        return calculate(cost, 1, n);
    }

    private int calculate(int[][] cost, int start, int end) {
        if (start >= end) return 0;
        if (cost[start][end] != 0) return cost[start][end];

        int res = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            // When you choose an x where start <= i <= end, you may find the target
            // number from left start...i-1, or you may find the target number from
            // the i+1...end, because you don't know which way should go, so to
            // guarantee you have enough money to find the target, you need to
            // prepare the more, which is max(dp[start][i-1], dp[i+1][end]).
            int worst = i + Math.max(calculate(cost, start, i - 1), calculate(cost, i + 1, end));
            res = Math.min(res, worst);
        }
        cost[start][end] = res;
        return res;
    }

    public int getMoneyAmount(int n) {
        int[][] cost = new int[n + 2][n + 2];
        for (int len = 1; len < n; ++len) {
            for (int i = 1; i + len <= n; ++i) {
                cost[i][i + len] = Integer.MAX_VALUE;
                for (int j = i; j <= i + len; ++j) {
                    cost[i][i + len] = Math.min(cost[i][i + len], Math.max(cost[i][j - 1], cost[j + 1][j]) + j);
                }
            }
        }
        return cost[1][n];
    }

    @Test
    public void test0() {
        assertEquals(1, getMoneyAmount0(2));
    }

    @Test
    public void test1() {
        assertEquals(16, getMoneyAmount(10));
    }
}
