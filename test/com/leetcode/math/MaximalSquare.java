package com.leetcode.math;

public class MaximalSquare {
	 public int maximalSquare(char[][] matrix) {
	        if(matrix==null || matrix.length==0) return 0;
	        int max = 0;
	        int[][] s = new int[matrix.length][matrix[0].length];
	        for(int i=0;i<matrix.length;i++) {
	        	for(int j=0;j<matrix[0].length;j++) {
	        		if(i==0||j==0) s[i][j] = matrix[i][j] - '0';
	        		else if(matrix[i][j]=='0') {
	        			s[i][j] = 0;
	        		} else {
	        			s[i][j] = Math.min(s[i-1][j], Math.min(s[i-1][j-1], s[i][j-1])) + 1;
	        		}
	        		if(s[i][j] > max) max = s[i][j];
	        	}
	        }
	        return max;
	    }
	 
}
