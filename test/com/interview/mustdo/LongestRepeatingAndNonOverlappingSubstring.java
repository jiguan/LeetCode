package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Given a string str, find the longest repeating non-overlapping substring in it. In other words find 2 identical
 * substrings of maximum length which do not overlap. If there exists more than one such substring return any of them.
 * 
 * Input : str = "geeksforgeeks" Output : geeks
 */
public class LongestRepeatingAndNonOverlappingSubstring {

    public String longestRepeatedSubstring(String str) {
        int n = str.length();

        // storing the substring length
        int[][] dp = new int[n + 1][n + 1];

        String res = "";
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (str.charAt(i) == str.charAt(j) && dp[i][j] < j - i) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;

                    if (dp[i + 1][j + 1] > res.length()) {
                        res = str.substring(i + 1 - dp[i + 1][j + 1], i + 1);
                    }
                }
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String str = "geeksforgeeks";
        String expected = "geeks";
        assertEquals(expected, longestRepeatedSubstring(str));
    }

    @Test
    public void test1() {
        String str = "aab";
        String expected = "a";
        assertEquals(expected, longestRepeatedSubstring(str));
    }

    @Test
    public void test2() {
        String str = "aabaabaaba";
        String expected = "aaba";
        assertEquals(expected, longestRepeatedSubstring(str));
    }

    @Test
    public void test3() {
        String str = "aaaaaaaaaaa";
        String expected = "aaaaa";
        assertEquals(expected, longestRepeatedSubstring(str));
    }

    @Test
    public void test4() {
        String str = "banana";
        String expected = "an";
        assertEquals(expected, longestRepeatedSubstring(str));
    }
}
