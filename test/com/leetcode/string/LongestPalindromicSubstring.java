package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); ++i) {
            String str1 = find(s, i, i);
            if (res.length() < str1.length()) res = str1;
            String str2 = find(s, i, i + 1);
            if (res.length() < str2.length()) res = str2;
        }
        return res;
    }

    private String find(String s, int low, int high) {
        while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            --low;
            ++high;
        }

        return s.substring(low + 1, high);
    }

    @Test
    public void test0() {
        String s = "a";
        assertEquals(s, longestPalindrome(s));
    }
}
