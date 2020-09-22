package com.leetcode.array.twopointers;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        int res = 0;
        for (int numUniqueTarget = 1; numUniqueTarget <= 26; numUniqueTarget++)
            res = Math.max(res, longestSubstringWithNUniqueChars(s, k, numUniqueTarget));
        return res;
    }

    private int longestSubstringWithNUniqueChars(String s, int k, int numUniqueTarget) {
        int[] map = new int[128];
        int numUnique = 0; // counter 1
        int numNoLessThanK = 0; // counter 2
        int begin = 0, end = 0;
        int maxLen = 0;

        while (end < s.length()) {
            if (map[s.charAt(end)]++ == 0)
                numUnique++; // increment map[c] after this statement
            if (map[s.charAt(end++)] == k)
                numNoLessThanK++; // inc end after this statement

            while (numUnique > numUniqueTarget) {
                if (map[s.charAt(begin)]-- == k)
                    numNoLessThanK--; // decrement map[c] after this statement
                if (map[s.charAt(begin++)] == 0)
                    numUnique--; // inc begin after this statement
            }

            // if we found a string where the number of unique chars equals our target
            // and all those chars are repeated at least K times then update max
            if (numUnique == numUniqueTarget && numUnique == numNoLessThanK)
                maxLen = Math.max(end - begin, maxLen);
        }

        return maxLen;
    }

    @Test
    public void test0() {
        String s = "ababbc";
        int k = 2;
        assertEquals(5, longestSubstring(s, k));
    }
}
