package com.leetcode.slidingwindow;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] times = new int[128];
        int begin = 0, end = 0, diff = 0;
        int maxLength = 0;
        while(end < s.length()) {
            char c = s.charAt(end++);
            // first time
            if(++times[c] == 1) {
                ++diff;
            }
            // at most two distinct characters, need to kick out one if larger than 2
            while(diff > 2) {
                char kickout = s.charAt(begin++);
                if(--times[kickout] == 0) {
                    --diff;
                }
            }
            maxLength = Math.max(maxLength, end - begin);
        }
        return maxLength;
    }
    
    @Test
    public void test0() {
        String s = "CAABAAAC";
        String expected_str = "AABAAA";
        assertEquals(expected_str.length(), lengthOfLongestSubstringTwoDistinct(s));
    }
}
