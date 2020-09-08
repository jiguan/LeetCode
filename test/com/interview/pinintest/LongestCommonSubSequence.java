package com.interview.pinintest;

// Java implementation of finding length of longest 
// Common substring using Dynamic Programming 
public class LongestCommonSubSequence 
{ 
    static int LCSubStr(String X, String Y) 
    { 
        char[] xc = X.toCharArray();
        char[] yc = Y.toCharArray();
        int xLen = xc.length, yLen = yc.length;
        int[][] dp = new int[xLen][yLen];
        int res = 0;
        for(int i = 0; i < xLen; i++) {
            for(int j = 0; j < yLen; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (xc[i-1] == yc[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    res = Math.max(res, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return res;
    } 
    

    static int LCSubStr2(String X, String Y) 
    { 
        char[] xc = X.toCharArray();
        char[] yc = Y.toCharArray();
        int xLen = xc.length, yLen = yc.length;
        int[][] dp = new int[2][yLen];
        int res = 0, current = 0;
        for(int i = 0; i < xLen; i++) {
            for(int j = 0; j < yLen; j++) {
                if(i == 0 || j == 0) {
                    dp[current][j] = 0;
                } else if (xc[i-1] == yc[j-1]) {
                    dp[current][j] = dp[1-current][j-1] + 1;
                    res = Math.max(res, dp[current][j]);
                } else {
                    dp[current][j] = 0;
                }
            }
            current = 1-current;
        }
        return res;
    } 
    
    // Driver Program to test above function 
    public static void main(String[] args) 
    { 
        String X = "OldSite:GeeksforGeeks.org"; 
        String Y = "NewSite:GeeksQuiz.com"; 

        System.out.println("Length of Longest Common Substring is "
                + LCSubStr(X, Y)); 
    } 
} 


// save space


