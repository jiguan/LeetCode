package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        int prevLen = 0, curLen = 1, res = 0;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) curLen++;
            else {
                prevLen = curLen;
                curLen = 1;
            }
            if (prevLen >= curLen) res++;
        }
        return res;
    }

    @Test
    public void test0() {
        String s = "0011";
        assertEquals(Integer.valueOf(4), Integer.valueOf(countBinarySubstrings(s)));
    }
}
