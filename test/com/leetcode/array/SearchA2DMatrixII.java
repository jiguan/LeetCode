package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SearchA2DMatrixII {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i = matrix.length - 1, j = 0;
        while (i >= 0 && j < matrix[0].length) {
            int num = matrix[i][j];
            if (num > target) {
                --i;
            } else if (num < target) {
                ++j;
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test0() {
        // @formatter:off
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, 
                                     {2, 5, 8, 12, 19}, 
                                     {3, 6, 9, 16, 22}, 
                                     {10, 13, 14, 17, 24}, 
                                     {18, 21, 23, 26, 30}};
        // @formatter:on
        assertTrue(searchMatrix(matrix, 5));
    }
}
