package com.leetcode.array.slidingwindow;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Given a string that consists of only uppercase English letters, you can replace any letter in the
 * string with another letter at most k times. Find the length of a longest substring containing all
 * repeating letters you can get after performing the above operations.
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int start = 0, maxCount = 0, maxLength = 0;
        for (int end = 0; end < len; end++) {
            // within the range from start to end, maxCount
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 > k + maxCount) {
                count[s.charAt(start) - 'A']--;
                start++;
            }
            maxLength = Math.max(maxLength, end - start + 1);
        }
        return maxLength;
    }

    @Test
    public void test0() {
        String s = "ABAB";
        int k = 2;
        assertEquals(4, characterReplacement(s, k));
    }

    @Test
    public void test1() {
        String s = "ABCADEA";
        int k = 1;
        assertEquals(2, characterReplacement(s, k));
    }
}
