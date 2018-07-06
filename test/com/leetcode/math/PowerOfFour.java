package com.leetcode.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PowerOfFour {
    public boolean isPowerOfFour0(int num) {
        if (num > 1) {
            while (num % 4 == 0) {
                num /= 4;
            }
        }
        return num == 1;
    }

    public boolean isPowerOfFour(int num) {
        // x^n - 1 = (x - 1)*(x^(n-1)+...+x+1);
        // so x^n - 1 has a factor x - 1. here x = 4.
        return num > 0 && (num & (num - 1)) == 0 && (num - 1) % 3 == 0;
    }

    public boolean isPowerOfFour1(int num) {
        if (num <= 0) return false;
        double r = Math.log10(num) / Math.log10(4);
        if (r % 1.0 == 0) return true;
        return false;
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
