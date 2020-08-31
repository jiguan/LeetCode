package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * 1568. Minimum Number of Days to Disconnect Island
 * 
 * Given a 2D grid consisting of 1s (land) and 0s (water). An island is a maximal 4-directionally
 * (horizontal or vertical) connected group of 1s.
 * 
 * The grid is said to be connected if we have exactly one island, otherwise is said disconnected.
 * 
 * In one day, we are allowed to change any single land cell (1) into a water cell (0).
 * 
 * Return the minimum number of days to disconnect the grid.
 * 
 * => Convert one island to more than one / zero island(s) => At most 2 steps, since we can convert
 * {{1, 1}, {1, 1}} to {{0, 1}, {1, 0}} (2 islands)
 * 
 */
// O((mn)^2)
public class MinimumNumberOfDaysToDisconnectIsland {
    public int minDays(int[][] grid) {
        if (countIsland(grid) != 1) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    if (countIsland(grid) != 1) {
                        return 1;
                    }
                    grid[i][j] = 1;
                }
            }
        }
        return 2;
    }

    private int countIsland(int[][] grid) {
        int ans = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    ans++;
                    dfs(visited, grid, i, j);
                }
            }
        }
        return ans;
    }

    private void dfs(boolean[][] visited, int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || visited[i][j]
                || grid[i][j] == 0) {
            return;
        }
        visited[i][j] = true;
        dfs(visited, grid, i - 1, j);
        dfs(visited, grid, i + 1, j);
        dfs(visited, grid, i, j - 1);
        dfs(visited, grid, i, j + 1);
    }

    @Test
    public void test0() {

        int[][] grid = {{0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        assertEquals(2, minDays(grid));
    }

}
