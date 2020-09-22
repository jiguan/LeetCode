package com.leetcode.dp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        char[] t1 = text1.toCharArray(), t2 = text2.toCharArray();
        int cache[][] = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    cache[i][j] = 0;
                } else if (t1[i - 1] == t2[j - 1]) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
                }
            }
        }
        return cache[m][n];
    }

    @Test
    public void test0() {
        String text1 = "abcde", text2 = "ace";
        assertEquals(3, longestCommonSubsequence(text1, text2));
    }
}
