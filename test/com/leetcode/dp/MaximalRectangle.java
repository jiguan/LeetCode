package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        //left the first 1's lower bound, right is last 1's upper bound
        int[] left = new int[n], right = new int[n], height = new int[n];
        for(int i=0;i<right.length;i++) {
            right[i] = n;
        }
        int result = 0;
        for(int i=0;i<m;i++) {
            int cur_left = 0, cur_right = n;
            for(int j=0;j<n;j++) {
                if(matrix[i][j]=='1') height[j]++;
                else height[j] = 0;
            }
            for(int j=0;j<n;j++) {
                if(matrix[i][j]=='1') left[j] = Math.max(left[j], cur_left);
                else {
                    left[j] = 0;
                    //pointing to next index
                    cur_left = j+1;
                }
            }
            for(int j=n-1;j>=0;j--) {
                if(matrix[i][j]=='1') right[j] = Math.min(right[j], cur_right);
                else {
                    right[j] = n;
                    //left one's upper bound is still current index
                    cur_right = j;
                }
            }
            for(int j=0;j<n;j++) {
                result = Math.max(result, (right[j] - left[j]) * height[j]);
            }
        }
        return result;
    }
    
    @Test
    public void test0() {
        char[][] matrix = new char[][]{{'0','1','1'},
            {'0','1','1'}};
        assertEquals(4, maximalRectangle(matrix));
    }
    
    @Test
    public void test1() {
        char[][] matrix = new char[][]{
            {'0','1','1','0','1'},
            {'1','1','0','1','0'},
            {'0','1','1','1','0'},
            {'1','1','1','1','0'},
            {'1','1','1','1','1'},
            {'0','0','0','0','0'}};
        assertEquals(9, maximalRectangle(matrix));
    }
}
