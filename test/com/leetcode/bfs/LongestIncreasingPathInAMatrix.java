package com.leetcode.bfs;

public class LongestIncreasingPathInAMatrix {
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] cache = new int[m][n];
        int res = 1;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int len = dfs(matrix, i, j, cache);
                res = Math.max(res, len);
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j];
        int res = 1;
        for (int[] dir : dirs) {
            int x = i + dir[0], y = j + dir[1];
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) continue;
            if (matrix[x][y] > matrix[i][j]) {
                res = Math.max(res, 1 + dfs(matrix, x, y, cache));
            }
        }
        cache[i][j] = res;
        return res;
    }
}
