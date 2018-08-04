package com.interview.mustdo;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class Minesweeper {
    public String[][] generate(int m, int n, double p) {
        // add boundary
        boolean[][] bombs = new boolean[m + 2][n + 2];

        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; j++) {
                bombs[i][j] = Math.random() < p;
            }
        }
        
        PrettyPrint.print(bombs);
        
        int[][] nums = new int [m + 2][n +2];
        
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if(bombs[i][j]) continue;
                // (ii, jj) indexes neighboring cells
                for (int ii = i - 1; ii <= i + 1; ++ii) {
                    for (int jj = j - 1; jj <= j + 1; ++jj) {
                        if (bombs[ii][jj]) nums[i][j]++;
                    }
                }
            }
        }

        PrettyPrint.print(nums);
        
        String[][] res = new String[m][n];
        for(int i = 0; i < m; ++i) {
            for(int j = 0; j < n; ++j) {
                if(bombs[i +1][j + 1]) res[i][j] = "X";
                else res[i][j] = String.valueOf(nums[i +1][j+1]);
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int m = 5, n = 10;
        double p = 0.3;
        String[][] res = generate(m, n, p);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}
