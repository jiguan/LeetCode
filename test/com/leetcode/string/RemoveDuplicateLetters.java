package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return s;
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            // find the letter with smallest ASCII value
            if (s.charAt(i) < s.charAt(pos)) {
                pos = i;
            }
            // if there is no more duplicated letter, a smaller character letter is useless
            if (--counts[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }

        String next = s.substring(pos + 1).replaceAll(String.valueOf(s.charAt(pos)), "");
        // only take out the char at pos, since letters before it have duplication letter (otherwise
        // we have stopped)
        return s.charAt(pos) + removeDuplicateLetters(next);
    }

    @Test
    public void test0() {
        String s = "cbacdcbc";
        assertEquals("acdb", removeDuplicateLetters(s));
    }
}
