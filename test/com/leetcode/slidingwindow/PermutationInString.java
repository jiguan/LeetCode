package com.leetcode.slidingwindow;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int[] times = new int[26];
        for (char c : s1.toCharArray()) {
            ++times[c - 'a'];
        }

        int begin = 0, end = 0, diff = s1.length();
        while (end < s2.length()) {
            char c = s2.charAt(end++);
            if(--times[c - 'a'] >= 0) {
                --diff;
            }
            
            if(diff == 0) return true;
            if(end - begin == s1.length()) {
                char kickout = s2.charAt(begin++);
                if(++times[kickout - 'a'] >= 1) ++diff;
            }
        }
        return false;
    }
    
    @Test
    public void test0() {
        String s1 = "ab", s2 = "eidbaooo";
        assertTrue(checkInclusion(s1, s2));
    }

    @Test
    public void test1() {
        String s1 = "abcdxabcde", s2 = "abcdeabcdx";
        assertTrue(checkInclusion(s1, s2));
    }
    
    @Test
    public void test2() {
        String s1 = "hello", s2 = "ooolleoooleh";
        assertFalse(checkInclusion(s1, s2));
    }
}
