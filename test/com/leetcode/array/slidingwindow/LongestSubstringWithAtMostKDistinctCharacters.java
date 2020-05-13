package com.leetcode.array.slidingwindow;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LongestSubstringWithAtMostKDistinctCharacters {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {

        int[] count = new int[26];
        int start = 0;
        int res = 0;
        for (int end = 0; end < s.length(); ++end) {
            char ch = s.charAt(end);
            if (count[ch - 'a']++ == 0) {
                --k;
            }
            
            while(k < 0) {
                char kick = s.charAt(start++);
                if (--count[kick - 'a'] == 0) {
                    ++k;
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }

    @Test
    public void test0() {
        String s = "eceba";
        int k = 2;
        // "ece"
        assertEquals(3, lengthOfLongestSubstringKDistinct(s, k));
    }
}
