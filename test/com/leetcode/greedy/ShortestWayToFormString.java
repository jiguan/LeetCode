package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * From any string, we can form a subsequence of that string by deleting some number of characters
 * (possibly no deletions).
 * 
 * Given two strings source and target, return the minimum number of subsequences of source such
 * that their concatenation equals target. If the task is impossible, return -1.
 */
public class ShortestWayToFormString {
    public int shortestWay(String source, String target) {
        int[][] map = new int[26][source.length()];
        for (int letter = 0; letter < 26; letter++) {
            int idx = -1;
            for (int i = source.length() - 1; i >= 0; i--) {
                if (source.charAt(i) - 'a' == letter) {
                    idx = i;
                }
                map[letter][i] = idx;
            }
        }
        int res = 0, start = -1;
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            if (map[c - 'a'][0] == -1) return -1;
            // find index after current anchor
            start = map[c - 'a'][start + 1];
            // if char is not found, re-search it again from the beginning
            if (start == -1) {
                res++;
                i--;
            } else if (start == source.length() - 1) {
                res++;
                start = -1;
            }
        }
        if (start > -1) res++;
        return res;
    }

    @Test
    public void test0() {
        String source = "abc", target = "abcbc";
        // The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source
        // "abc".
        assertEquals(2, shortestWay(source, target));
    }

    @Test
    public void test1() {
        String source = "aaaaa", target = "aaaaaaaaaaaaa";
        assertEquals(3, shortestWay(source, target));
    }
}
