package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GuessNumberHigherOrLowerII {
    public int getMoneyAmount(int n) {
        int[][] matrix = new int[n+1][n+1];
        return dp(matrix, 1, n);
    }
    
    private int dp(int[][] matrix, int start, int end) {
        if(start>=end) return 0;
        if(matrix[start][end]!=0) return matrix[start][end]; 
        
        int res = Integer.MAX_VALUE;
        for(int i = start;i<=end;i++) {
            //the max part is tricky, it assume the opponent is not stupid and he will use the max output to payback
            int tmp = i + Math.max(dp(matrix, start, i-1), dp(matrix, i+1, end));
            res = Math.min(res, tmp);
        }
        matrix[start][end] = res;
        return res;
    }
    
    @Test
    public void test0() {
        System.out.println(getMoneyAmount(4));
    }
    
    @Test
    public void test1() {
        assertEquals(16, getMoneyAmount(10));
    }
}
