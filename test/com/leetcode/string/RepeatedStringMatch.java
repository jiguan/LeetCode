package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        StringBuffer sb = new StringBuffer();

        int count = 0;
        while (sb.length() - A.length() < B.length()) {
            sb.append(A);
            count++;
            if (sb.length() >= B.length() && sb.toString().contains(B)) {
                return count;
            }

        }
        return -1;
    }

    @Test
    public void test0() {
        String A = "abcd", B = "cdabcdab";
        assertEquals(Integer.valueOf(3), Integer.valueOf(repeatedStringMatch(A, B)));
    }

    @Test
    public void test1() {
        String A = "a", B = "aa";
        assertEquals(Integer.valueOf(2), Integer.valueOf(repeatedStringMatch(A, B)));
    }
}
