package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// Expedia
public class MinimumInsertionsToFormAPalindrome {
    int findMinInsertionsDP(String str) {
        int n = str.length();
        // Create a table of size n*n. table[i][j]
        // will store minumum number of insertions
        // needed to convert str[i..j] to a palindrome.
        int dp[][] = new int[n][n];

        for (int gap = 1; gap < n; ++gap) {
            for (int start = 0, end = gap; end < n; ++start, ++end) {
                if (str.charAt(start) == str.charAt(end)) {
                    dp[start][end] = dp[start + 1][end - 1];
                } else {
                    dp[start][end] = Integer.min(dp[start][end - 1], dp[start + 1][end]) + 1;
                }
            }
        }

        return dp[0][n - 1];
    }

    @Test
    public void test0() {
        String str = "abca";
        assertEquals(1, findMinInsertionsDP(str));
    }
}
