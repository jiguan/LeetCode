package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ShortestWayToFormStringFollowUp {
    public int shortestWay(String source, String target) {
        char[] s = source.toCharArray(), t = target.toCharArray();
        // for one letter, what is the next occurrence index after i in S
        int[][] idx = new int[26][source.length()];
        for (int i = 0; i < s.length; i++) {
            idx[s[i] - 'a'][i] = i + 1;
        }
        for (int letter = 0; letter < 26; letter++) {
            int prev = 0;
            for (int i = s.length - 1; i >= 0; i--) {
                if (idx[letter][i] == 0) {
                    idx[letter][i] = prev;
                } else {
                    prev = idx[letter][i];
                }
            }
        }
        int res = 1;
        for (int i = 0, j = 0; j < t.length; j++) {
            if (i == s.length) {
                i = 0;
                res++;
            }
            if (idx[t[j] - 'a'][0] == 0) {
                return -1;
            }
            i = idx[t[j] - 'a'][i];
            if (i == 0) {
                res++;
                j--;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        String source = "abc", target = "abcbc";
        assertEquals(2, shortestWay(source, target));
    }
}
