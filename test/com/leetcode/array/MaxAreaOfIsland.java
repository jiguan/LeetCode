package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                // Mark it invalid in the same array is a good trick
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }

        return res;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i >= 0 && j >= 0 && i < grid.length && j < grid[0].length
                && grid[i][j] == 1) {
            grid[i][j] = 0;
            return 1 + dfs(grid, i + 1, j) + dfs(grid, i, j + 1)
                    + dfs(grid, i - 1, j) + dfs(grid, i, j - 1);
        }
        return 0;
    }

    @Test
    public void test0() {
        int[][] grid = new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
        assertEquals(4, maxAreaOfIsland(grid));
    }

    @Test
    public void test2() {
        int[][] grid = new int[][]{{1, 0, 1, 0, 0}, {1, 1, 1, 0, 0},
                {0, 0, 1, 0, 0}, {1, 1, 0, 0, 0}};

        assertEquals(6, maxAreaOfIsland(grid));
    }

    @Test
    public void test1() {
        int[][] grid = new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};

        assertEquals(6, maxAreaOfIsland(grid));
    }
}
