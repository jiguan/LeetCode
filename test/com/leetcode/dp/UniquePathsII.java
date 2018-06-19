package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid.length == 0) return 0;

        int[][] paths = new int[obstacleGrid.length][obstacleGrid[0].length];
        if (obstacleGrid[0][0] == 0) paths[0][0] = 1;

        for (int i = 0; i < paths.length; i++) {
            for (int j = 0; j < paths[0].length; j++) {
                if ((i == 0 && j == 0) || obstacleGrid[i][j] == 1) continue;
                paths[i][j] = (i > 0 ? paths[i - 1][j] : 0) + (j > 0 ? paths[i][j - 1] : 0);
            }
        }
        return paths[paths.length - 1][paths[0].length - 1];
    }

    @Test
    public void test0() {
        int[][] obstacleGrid = new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
        assertEquals(2, uniquePathsWithObstacles(obstacleGrid));
    }

    @Test
    public void test1() {
        int[][] obstacleGrid = new int[][] { { 0, 0 }, { 0, 0 } };
        assertEquals(2, uniquePathsWithObstacles(obstacleGrid));
    }

}
