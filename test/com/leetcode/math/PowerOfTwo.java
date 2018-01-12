package com.leetcode.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if (n > 1) {
            while (n % 2 == 0) {
                n /= 2;
            }
        }
        return n == 1;
    }

    @Test
    public void test0() {
        int n = 4;
        assertTrue(isPowerOfTwo(n));
    }

    @Test
    public void test1() {
        int n = 11;
        assertFalse(isPowerOfTwo(n));
    }

    @Test
    public void test2() {
        int n = 0;
        assertFalse(isPowerOfTwo(n));
    }

    @Test
    public void test3() {
        int n = -2;
        assertFalse(isPowerOfTwo(n));
    }

}
