package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int dp[][] = new int[matrix.length + 1][matrix[0].length + 1];
        int edge = 0;
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] == '1') {
                    // Make sure it is solid
                    dp[i + 1][j + 1] = Math.min(Math.min(dp[i + 1][j], dp[i][j + 1]), dp[i][j]) + 1;
                    edge = Math.max(edge, dp[i + 1][j + 1]);
                }
            }
        }
        return edge * edge;
    }

    @Test
    public void test0() {
        char[][] matrix = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
        assertEquals(4, maximalSquare(matrix));
    }
}
