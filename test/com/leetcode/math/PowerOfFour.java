package com.leetcode.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        if (num > 1) {
            while (num % 4 == 0) {
                num /= 4;
            }
        }
        return num == 1;
    }

    @Test
    public void test0() {
        int num = 8;
        assertFalse(isPowerOfFour(num));
    }

    @Test
    public void test1() {
        int num = 16;
        assertTrue(isPowerOfFour(num));
    }

    @Test
    public void test2() {
        int num = 1;
        assertTrue(isPowerOfFour(num));
    }

    @Test
    public void test3() {
        int num = 7;
        assertFalse(isPowerOfFour(num));
    }

    @Test
    public void test4() {
        int num = 12;
        assertFalse(isPowerOfFour(num));
    }
}
