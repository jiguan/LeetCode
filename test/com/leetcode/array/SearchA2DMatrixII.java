package com.leetcode.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SearchA2DMatrixII {
    public boolean searchMatrix0(int[][] matrix, int target) {
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

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = m - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (matrix[mid][n -1] < target) {
                start = mid + 1;
            } else if (matrix[mid][0] > target) {
                end = mid - 1;
            } else {
                for (int i = start; i <= end; ++i) {
                    if (search(matrix[i], target)) return true;
                }
                return false;
            }
        }
        return false;
    }

    private boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
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
    
    @Test
    public void test1() {
        // @formatter:off
        int[][] matrix = new int[][]{{1, 4, 7, 11, 15}, 
                                     {2, 5, 8, 12, 19}, 
                                     {3, 6, 9, 16, 22}, 
                                     {10, 13, 14, 17, 24}, 
                                     {18, 21, 23, 26, 30}};
        // @formatter:on
        assertFalse(searchMatrix(matrix, 20));
    }
    
    @Test
    public void test2() {
        // @formatter:off
        int[][] matrix = new int[][]{{-5},{-5}};
        // @formatter:on
        assertFalse(searchMatrix(matrix, 0));
    }
}
