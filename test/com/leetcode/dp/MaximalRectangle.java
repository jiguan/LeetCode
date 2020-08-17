package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        // lower bound of consecutive '1' index
        int[] left = new int[n];
        // upper bound of consecutive '1' index, excluded
        int[] right = new int[n];
        // accumulate the height, from top to bottom
        int[] height = new int[n];
        for (int j = 0; j < n; j++) {
            right[j] = n;
        }
        int result = 0;
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {  
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    // we calculate res row by row, so we need to reset
                    height[j] = 0;
                }
            }
            int currLeft = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    // max is to get the common area
                    // 0 0 1 => 0 0 2
                    // 1 1 1 => 0 0 2 (not 0 0 0)
                    left[j] = Math.max(left[j], currLeft);
                else {
                    left[j] = 0;
                    // move to next possible '1' index
                    currLeft = j + 1;
                }
            }
            // current right boundary
            int currRight = n;
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1')
                    // min is to get the common area
                    // 1 0 0 => 1 3 3
                    // 1 1 1 => 1 3 3 (not 3 3 3)
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
        char[][] matrix = new char[][] {{'1', '1', '0'}, {'1', '1', '1'}, {'1', '1', '0'}};
        assertEquals(4, maximalRectangle(matrix));
    }

    @Test
    public void test1() {
        // @formatter:off
        char[][] matrix = new char[][] {
            {'0', '1', '1', '0', '1'},
            {'1', '1', '0', '1', '0'},
            {'0', '1', '1', '1', '0'},
            {'1', '1', '1', '1', '0'},
            {'1', '1', '1', '1', '1'},
            {'0', '0', '0', '0', '0'}};
        // @formatter:on
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

    @Test
    public void test4() {
        // @formatter:off
        char[][] matrix = new char[][] {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}};
        // @formatter:on
        assertEquals(6, maximalRectangle(matrix));
    }
    
    @Test
    public void test5() {
        char[][] matrix = new char[][] {{'1', '1', '0'}, {'0', '0', '1'}, {'1', '1', '0'}};
        assertEquals(1, maximalRectangle(matrix));
    }
    
    
    @Test
    public void test6() {
        char[][] matrix = new char[][] {{'1', '1', '1'}, {'1', '0', '1'}, {'0', '0', '1'}};
        assertEquals(3, maximalRectangle(matrix));
    }
}
