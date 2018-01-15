package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class PacificAtlanticWaterFlow {
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        boolean[][] pacific = new boolean[matrix.length][matrix[0].length];
        boolean[][] atlantic = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            dfs(matrix, i, 0, pacific, Integer.MIN_VALUE); // left
            dfs(matrix, i, matrix[0].length - 1, atlantic, Integer.MIN_VALUE); // right
        }

        for (int j = 0; j < matrix[0].length; ++j) {
            dfs(matrix, 0, j, pacific, Integer.MIN_VALUE); // top
            dfs(matrix, matrix.length - 1, j, atlantic, Integer.MIN_VALUE); // bottom
        }

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (pacific[i][j] && atlantic[i][j]) {
                    res.add(new int[] { i, j });
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, int x, int y, boolean[][] visited, int height) {
        // from pacific/atlantic to center, height is from low to high
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || visited[x][y] || matrix[x][y] < height)
            return;
        visited[x][y] = true;

        int[][] directions = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
        for (int[] dir : directions) {
            dfs(matrix, x + dir[0], y + dir[1], visited, matrix[x][y]);
        }
    }

    @Test
    public void test0() {
        int[][] matrix = new int[][] { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 },
                { 5, 1, 1, 2, 4 } };
        List<String> expected = Arrays.asList("0, 4", "1, 3","1, 4","2, 2","3, 0","3, 1","4, 0");
        List<String> actual = new LinkedList<>();
        for(int[] point :  pacificAtlantic(matrix)) {
            actual.add(point[0] + ", " + point[1]);
        }
        assertEquals(expected, actual);
    }
}
