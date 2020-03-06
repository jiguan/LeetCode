package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ReorganizeString {
    public String reorganizeString(String s) {
        int[] counts = new int[26];
        for (char ch : s.toCharArray()) {
            counts[ch - 'a']++;
        }

        int max = 0, letter = 0;
        for (int i = 0; i < counts.length; ++i) {
            if (max < counts[i]) {
                max = counts[i];
                letter = i;
            }
        }

        if (max > (s.length() + 1) / 2) return "";
        char[] res = new char[s.length()];
        int index = 0;
        while (counts[letter] > 0) {
            res[index] = (char) (letter + 'a');
            index += 2;
            counts[letter]--;
        }
        for (int i = 0; i < counts.length; ++i) {
            while (counts[i] > 0) {
                if (index >= res.length) {
                    index = 1;
                }
                res[index] = (char) (i + 'a');
                index += 2;
                counts[i]--;
            }
        }
        return String.valueOf(res);
    }

    @Test
    public void test0() {
        String s = "aab";
        String expected = "aba";
        assertEquals(expected, reorganizeString(s));
    }
}
