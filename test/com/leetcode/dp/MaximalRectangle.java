package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // index where '1' starts
        int[] left = new int[n];
        // index where '1' ends, (index where first '0' occurs)
        // end with n, just like the length, excluded
        int[] right = new int[n];
        // accumulate the height, from top to bottom
        int[] height = new int[n];
        for (int j = 0; j < n; j++) {
            right[j] = n;
        }
        int result = 0;
        for (int i = 0; i < m; i++) {
            int currLeft = 0, currRight = n;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    // we calculate res row by row, so we need to reset
                    height[j] = 0;
                }
            }
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    left[j] = Math.max(left[j], currLeft);
                else {
                    left[j] = 0;
                    // move to next possible '1' index
                    currLeft = j + 1;
                }
            }
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1')
                    right[j] = Math.min(right[j], currRight);
                else {
                    right[j] = n;
                    // since it is 0, update the '1' ends index to this index
                    currRight = j;
                }
            }
            for (int j = 0; j < n; j++) {
                result = Math.max(result, (right[j] - left[j]) * height[j]);
            }
        }
        return result;
    }

    @Test
    public void test0() {
        char[][] matrix = new char[][] {{'0', '1', '1'}, {'0', '1', '1'}};
        assertEquals(4, maximalRectangle(matrix));
    }

    @Test
    public void test1() {
        char[][] matrix = new char[][] {{'0', '1', '1', '0', '1'}, {'1', '1', '0', '1', '0'},
                {'0', '1', '1', '1', '0'}, {'1', '1', '1', '1', '0'}, {'1', '1', '1', '1', '1'},
                {'0', '0', '0', '0', '0'}};
        assertEquals(9, maximalRectangle(matrix));
    }

    @Test
    public void test2() {
        char[][] matrix = new char[][] {{'0', '0', '0', '1', '0', '0', '0'},
                {'0', '0', '1', '1', '1', '0', '0'}, {'0', '1', '1', '1', '1', '1', '0'}};
        assertEquals(9, maximalRectangle(matrix));
    }

    @Test
    public void test3() {
        char[][] matrix = new char[][] {{'0', '1'}};
        assertEquals(1, maximalRectangle(matrix));
    }
}
