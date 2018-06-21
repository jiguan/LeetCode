package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// adding characters in front of it only
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        int start = 0;
        for (int end = s.length() - 1; end >= 0; end--) {
            if (s.charAt(start) == s.charAt(end)) {
                start++;
            }
        }
        if (start == s.length()) {
            return s;
        }
        String suffix = s.substring(start);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, start)) + suffix;
    }

    // this method use KMP, not so ideal. Hard to think, hard to explain.
    public String shortestPalindrome0(String s) {
        // reverse s, if there is a match, then a palindrome is found
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = build(temp);

        // find out the suffix that is not palindrome
        String notPalindrome = s.substring(table[table.length - 1]);
        return new StringBuilder(notPalindrome).reverse().toString() + s;
    }

    private int[] build(String s) {
        int[] arr = new int[s.length()];

        for (int i = 1; i < s.length(); i++) {
            int j = arr[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = arr[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            arr[i] = j;
        }
        return arr;
    }

    @Test
    public void test0() {
        String s = "aacecaaa";
        assertEquals("aaacecaaa", shortestPalindrome(s));
    }

    @Test
    public void test1() {
        String s = "aabaac";
        // in this case, aabaac#caabaa, only the last c gets matched
        assertEquals("caabaac", shortestPalindrome(s));
    }

    @Test
    public void test2() {
        String s = "caabaa";
        assertEquals("aabaacaabaa", shortestPalindrome(s));
    }

    @Test
    public void test3() {
        String s = "adcba";
        assertEquals("abcdadcba", shortestPalindrome(s));
    }
    
    @Test
    public void test4() {
        String s = "abcdabca";
        assertEquals("acbadcbabcdabca", shortestPalindrome(s));
    }
}
