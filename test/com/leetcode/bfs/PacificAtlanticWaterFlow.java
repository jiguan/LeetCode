package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class PacificAtlanticWaterFlow {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        int m = matrix.length, n = matrix[0].length;
        if (m == 0 || n == 0) return res;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        for (int i = 0; i < m; ++i) {
            dfs(matrix, i, 0, pacific, Integer.MIN_VALUE); // left
            dfs(matrix, i, n - 1, atlantic, Integer.MIN_VALUE); // right
        }

        for (int j = 0; j < n; ++j) {
            dfs(matrix, 0, j, pacific, Integer.MIN_VALUE); // top
            dfs(matrix, m - 1, j, atlantic, Integer.MIN_VALUE); // bottom
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, int x, int y, boolean[][] visited, int height) {
        // from pacific/atlantic to center, height is from low to high
        int m = matrix.length, n = matrix[0].length;
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] || matrix[x][y] < height) return;
        visited[x][y] = true;

        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            dfs(matrix, x + dir[0], y + dir[1], visited, matrix[x][y]);
        }
    }

    @Test
    public void test0() {
        int[][] matrix = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}};
        List<String> expected = Arrays.asList("0, 4", "1, 3", "1, 4", "2, 2", "3, 0", "3, 1", "4, 0");
        List<String> actual = new LinkedList<>();
        for (int[] point : pacificAtlantic(matrix)) {
            actual.add(point[0] + ", " + point[1]);
        }
        assertEquals(expected, actual);
    }
}
