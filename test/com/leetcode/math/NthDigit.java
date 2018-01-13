package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NthDigit {
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        while (n > len * count) {
            n -= len * count;
            // length of the number
            len += 1;
            count *= 10;
            // number base
            start *= 10;
        }

        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }

    @Test
    public void test0() {
        assertEquals(3, findNthDigit(3));
    }

    @Test
    public void test1() {
        assertEquals(0, findNthDigit(11));
    }
}
