package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        boolean row0 = false, col0 = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; ++j) {
                // from left to right, mark should be the point we have visited
                if (matrix[i][j] == 0) {
                    if (i == 0) row0 = true;
                    if (j == 0) col0 = true;
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < m && col0; ++i) {
            matrix[i][0] = 0;
        }

        for (int j = 0; j < n && row0; ++j) {
            matrix[0][j] = 0;
        }
    }

    @Test
    public void test0() {
       //@formatter:off
        int[][] matrix = 
        {
            {0,1,2,0},
            {3,4,5,2},
            {1,3,1,5}
        };
       //@formatter:on
        setZeroes(matrix);
       //@formatter:off
        int[][] expected = 
        {
            {0, 0, 0, 0}, 
            {0, 4, 5, 0}, 
            {0, 3, 1, 0}
        };
       //@formatter:on
        assertArrayEquals(expected, matrix);
    }

    @Test
    public void test1() {
       //@formatter:off
        int[][] matrix = 
        {
            {1,1,1},
            {0,1,2}
        };
       //@formatter:on
        setZeroes(matrix);
       //@formatter:off
        int[][] expected = 
        {
            {0,1,1},
            {0,0,0}
        };
       //@formatter:on
        assertArrayEquals(expected, matrix);
    }
}
