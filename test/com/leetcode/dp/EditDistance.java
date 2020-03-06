package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if(m == 0 || n == 0) return m + n;
        int[][] dp = new int[m + 1][n + 1];
        
        for(int i = 1; i<=m;++i) {
            dp[i][0] = i;
        }
        for(int j = 1; j<=n;++j) {
            dp[0][j] = j;
        }
        
        for(int i = 1; i<=m; ++i) {
            for(int j = 1; j<=n;++j) {
                if(word1.charAt(i - 1) == word2.charAt(j -1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    @Test
    public void test0() {
        String s = "Saturday", r = "Sunday";
        assertEquals(3, minDistance(s, r));
    }

    @Test
    public void test1() {
        String s = "", r = "";
        assertEquals(0, minDistance(s, r));
    }

    @Test
    public void test2() {
        String s = "a", r = "a";
        assertEquals(0, minDistance(s, r));
    }

    @Test
    public void test3() {
        String s = "plasma", r = "altruism";
        assertEquals(6, minDistance(s, r));
    }
}
