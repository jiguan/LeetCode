package com.leetcode.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        // store the length of valid parentheses at position i
        int[] longest = new int[s.length()];

        int res = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    longest[i] = (i - 2 >= 0 ? longest[i - 2] : 0) + 2;
                } else {
                    // i - len: start index of dp[i-1]
                    // i - 1 - len, one before start index
                    int matchIndex = i - longest[i - 1] - 1;
                    if (matchIndex >= 0 && s.charAt(matchIndex) == '(') {
                        longest[i] = longest[i - 1] + 2;
                        if (matchIndex - 1 >= 0) {
                            longest[i] += longest[matchIndex - 1];
                        }
                    }
                }
                res = Math.max(res, longest[i]);
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String s = ")()())";
        assertEquals(4, longestValidParentheses(s));
    }

    @Test
    public void test1() {
        String s = ")(()))";
        assertEquals(4, longestValidParentheses(s));
    }

    @Test
    public void test3() {
        String s = ")()(())";
        assertEquals(6, longestValidParentheses(s));
    }
}
