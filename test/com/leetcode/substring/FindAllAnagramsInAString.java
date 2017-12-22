package com.leetcode.substring;

import static org.junit.Assert.assertArrayEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new LinkedList<>();
        if (p.length() > s.length()) return res;

        // char - times
        int[] times = new int[26];
        for (char c : p.toCharArray()) {
            ++times[c - 'a'];
        }

        int diff = p.length();
        int start = 0, end = 0;

        while (end < s.length()) {
            char c = s.charAt(end++);
            if (--times[c - 'a'] >= 0) --diff;

            if (diff == 0) res.add(start);
            if (end - start == p.length()) {
                char kickout = s.charAt(start++);
                if (++times[kickout - 'a'] >= 1) {
                    ++diff;
                }
            }

        }
        return res;
    }

    @Test
    public void test0() {
        String s = "cbaebabacd", p = "abc";
        List<Integer> list = findAnagrams(s, p);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            actual[i] = list.get(i);
        }
        assertArrayEquals(new int[]{0, 6}, actual);
    }

    @Test
    public void test1() {
        String s = "acdcaeccde", p = "c";
        List<Integer> list = findAnagrams(s, p);
        int[] actual = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            actual[i] = list.get(i);
        }
        assertArrayEquals(new int[]{1, 3, 6, 7}, actual);
    }

}
