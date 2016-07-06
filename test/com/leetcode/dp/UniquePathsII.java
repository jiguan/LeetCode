package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if(m==0) return 0;
        int n = obstacleGrid[0].length;
        int[][] paths = new int[m][n];
        for(int i=0;i<m;i++) { //i is m + 1
            if(obstacleGrid[i][0]==1) break;
            paths[i][0] = 1;
        }
        for(int j=0;j<n;j++) { //i is n + 1
            if(obstacleGrid[0][j]==1) break;
            paths[0][j] = 1;
        }
        for(int i=1;i<m;i++) {
            for(int j=1;j<n;j++) {
                if(obstacleGrid[i][j]==1)  paths[i][j] = 0;
                else {
                    paths[i][j] = paths[i-1][j] + paths[i][j-1]; 
                }
            }
        }
        return paths[m-1][n-1];
    }
    
    @Test
    public void test0() {
        int[][] obstacleGrid = new int[][] {
            {0,0,0},
            {0,1,0},
            {0,0,0}
        };
        assertEquals(2, uniquePathsWithObstacles(obstacleGrid));
    }
    
    @Test
    public void test1() {
        int[][] obstacleGrid = new int[][] {
            {0,0,0},
            {0,1,0},
            {0,1,0}
        };
        assertEquals(1, uniquePathsWithObstacles(obstacleGrid));
    }
    
}
