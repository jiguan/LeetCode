package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Pow {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    private static final double DELTA = 1e-15;

    @Test
    public void test0() {
        int x = 16, n = 2;
        assertEquals(Math.pow(x, n), myPow(x, n), DELTA);
    }

    @Test
    public void test1() {
        int x = 16, n = -3;
        assertEquals(Math.pow(x, n), myPow(x, n), DELTA);
    }
}
