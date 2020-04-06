package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LastSubstringInLexicographicalOrder {
    public String lastSubstring(String s) {
        int len = s.length();
        int i = 0, j = 1, k = 0;
        while (j + k < len) {
            if (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
                continue;
            }
            if (s.charAt(i + k) > s.charAt(j + k)) {
                j++;
            } else {
                i = j;
                j = i + 1;
            }
            k = 0;
        }

        return s.substring(i);
    }

    @Test
    public void test0() {
        String s = "leetcode";
        String expected = "tcode";
        assertEquals(expected, lastSubstring(s));
    }
}
