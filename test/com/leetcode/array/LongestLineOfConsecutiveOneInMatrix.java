package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be
 * horizontal, vertical, diagonal or anti-diagonal. Example:
 * 
 * Input: [[0,1,1,0], [0,1,1,0], [0,0,0,1]]
 * 
 * Output: 3 Hint: The number of elements in the given matrix will not exceed 10,000.
 */
public class LongestLineOfConsecutiveOneInMatrix {
    public int longestLine(int[][] M) {
        int m = M.length, max = 0;
        if (m == 0) return max;
        int n = M[0].length;
        int[][][] dp = new int[m][n][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (M[i][j] == 0) {
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = 1;
                }
                if (j > 0) {
                    dp[i][j][0] += dp[i][j - 1][0]; // horizontal line
                }
                if (j > 0 && i > 0) {
                    dp[i][j][1] += dp[i - 1][j - 1][1]; // anti-diagonal line
                }
                if (i > 0) {
                    dp[i][j][2] += dp[i - 1][j][2]; // vertical line
                }
                if (j < n - 1 && i > 0) {
                    dp[i][j][3] += dp[i - 1][j + 1][3]; // diagonal line
                }
                max = Math.max(max, Math.max(dp[i][j][0], dp[i][j][1]));
                max = Math.max(max, Math.max(dp[i][j][2], dp[i][j][3]));
            }
        }
        return max;
    }

    @Test
    public void test0() {
        int[][] M = {{0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}};
        assertEquals(3, longestLine(M));
    }
}
