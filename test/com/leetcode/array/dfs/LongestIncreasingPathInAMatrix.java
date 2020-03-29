package com.leetcode.array.dfs;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LongestIncreasingPathInAMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int res = 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                res = Math.max(res, dfs(matrix, i, j, cache));
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];

        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int res = 1;
        for (int[] dir : dirs) {
            int a = dir[0] + i, b = dir[1] + j;

            if (a >= 0 && a < matrix.length && b >= 0 && b < matrix[0].length
                    && matrix[a][b] > matrix[i][j]) {
                int tmp = 1 + dfs(matrix, a, b, cache);
                res = Math.max(res, tmp);
            }
        }
        cache[i][j] = res;
        return res;
    }

    @Test
    public void test0() {
        int[][] nums = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        assertEquals(4, longestIncreasingPath(nums));
    }
}

