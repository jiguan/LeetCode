package com.leetcode.dp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * '?' Matches any single character. '*' Matches any sequence of characters (including the empty sequence).
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int next_i = 0, starIndex = -1;
        while (i < s.length()) {
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                i++;
                j++;
            } else if (j < p.length() && p.charAt(j) == '*') {
                starIndex = j;
                next_i = i;
                j++;
            } else if (starIndex != -1) {
                j = starIndex + 1;
                // any character represented by *
                next_i++;
                // we cannot just i++, since if we are in this step, charAt(i) not match with charAt(j)
                i = next_i;
            } else {
                return false;
            }
        }

        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }

    @Test
    public void test0() {
        String s = "adceb", p = "*a*b";
        assertTrue(isMatch(s, p));
    }

    @Test
    public void test1() {
        String s = "acdcb", p = "a*c?b";
        assertFalse(isMatch(s, p));
    }

    @Test
    public void test2() {
        String s = "aa", p = "a";
        assertFalse(isMatch(s, p));
    }

    @Test
    public void test3() {
        String s = "aa", p = "*";
        assertTrue(isMatch(s, p));
    }

    @Test
    public void test4() {
        String s = "aefcdgiescdfimde", p = "a*cd?i*de";
        assertTrue(isMatch(s, p));
    }
    
    @Test
    public void test5() {
        String s = "abcdbbcde", p = "a*cde";
        assertTrue(isMatch(s, p));
    }
    
    @Test
    public void test6() {
        String s = "aaaa", p = "***a";
        assertTrue(isMatch(s, p));
    }
}
