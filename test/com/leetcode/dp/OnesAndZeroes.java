package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int[] count = count(str);

             for (int i = m; i >= count[0]; i--) {
                for (int j = n; j >= count[1]; j--) {
                    dp[i][j] = Math.max(1 + dp[i - count[0]][j - count[1]], dp[i][j]);
                }
            }

        }
        return dp[m][n];
    }

    private int[] count(String s) {
        int[] res = new int[2];
        for (char c : s.toCharArray()) {
            res[c - '0']++;
        }
        return res;
    }

    @Test
    public void test0() {
        String[] strs = new String[]{"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        assertEquals(Integer.valueOf(4), Integer.valueOf(findMaxForm(strs, m, n)));
    }
}
