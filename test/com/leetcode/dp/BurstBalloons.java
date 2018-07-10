package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BurstBalloons {
    public int maxCoins0(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) {
            if (x > 0) nums[n++] = x;
        }
        nums[0] = 1;
        nums[n++] = 1;

        int[][] dp = new int[n][n];
        return burst(dp, nums, 0, n - 1);
    }

    public int burst(int[][] dp, int[] nums, int left, int right) {
        // every times, must burst 3 balloons
        if (left + 1 == right) return 0;
        if (dp[left][right] > 0) return dp[left][right];
        int ans = 0;
        for (int i = left + 1; i < right; ++i) {
            ans = Math.max(ans,
                    nums[left] * nums[i] * nums[right] + burst(dp, nums, left, i) + burst(dp, nums, i, right));
        }
        dp[left][right] = ans;
        return ans;
    }

    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        nums[0] = 1;
        int n = 1;
        for (int x : iNums)
            if (x > 0) nums[n++] = x;
        nums[n++] = 1;

        int[][] dp = new int[n][n];
        // k is the index difference between left and right, since left, i, right, the min is 2
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i)
                    dp[left][right] = Math.max(dp[left][right],
                            nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
            }

        return dp[0][n - 1];
    }

    @Test
    public void test0() {
        int[] nums = {3, 1};
        assertEquals(6, maxCoins(nums));
    }
}
