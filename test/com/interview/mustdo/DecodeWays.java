package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DecodeWays {
    public int recursive(String s) {
        if (s.isEmpty()) {
            return 1;
        }

        if (s.charAt(0) == '0') {
            return 0;
        }

        if (s.length() == 1) return 1;

        int sub1 = numDecodings(s.substring(1));
        int sub2 = 0;

        if (Integer.parseInt(s.substring(0, 2)) <= 26) {
            sub2 = numDecodings(s.substring(2));
        }
        return sub1 + sub2;
    }

    public int dp(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') {
            return 0;
        }
        int sub1 = 1;
        int sub2 = 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i + 1) == '0') {
                sub1 = 0;
            }
            if (Integer.parseInt(s.substring(i, i + 2)) <= 26) {
                sub1 = sub1 + sub2;
                sub2 = sub1 - sub2;
            } else {
                sub2 = sub1;
            }
        }
        return sub1;
    }

    public int numDecodings(String s) {
        // dp[1], decode string of size 1, dp[n] is the result
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return 0;

        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; ++i) {
            int one = Integer.valueOf(s.substring(i - 1, i));
            int two = Integer.valueOf(s.substring(i - 2, i));
            if (one >= 1 && one <= 9) {
                dp[i] += dp[i - 1];
            }
            if (two >= 10 && two <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    @Test
    public void test0() {
        String s = "226";
        assertEquals(3, numDecodings(s));
    }

    @Test
    public void test1() {
        String s = "01";
        assertEquals(0, numDecodings(s));
    }

    @Test
    public void test2() {
        String s = "101";
        assertEquals(1, numDecodings(s));
    }
}
