package com.leetcode.palindrome;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// adding characters in front of it only
public class ShortestPalindromeSuffix {
    public String shortestPalindrome(String s) {
        int end = s.length() - 1;
        for (int start = 0; start < s.length(); ++start) {
            if (s.charAt(start) == s.charAt(end)) {
                end--;
            }
        }
        if (end == -1) {
            return s;
        }
        String prefix = s.substring(0, end + 1);
        return prefix + shortestPalindrome(s.substring(end + 1)) + new StringBuffer(prefix).reverse().toString();
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
    public void test4() {
        String s = "abcdabca";
        assertEquals("abcdabcacbadcba", shortestPalindrome(s));
    }

    @Test
    public void test5() {
        String s = "abcd";
        assertEquals("abcdcba", shortestPalindrome(s));
    }
}
