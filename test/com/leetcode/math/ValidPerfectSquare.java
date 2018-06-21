package com.leetcode.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num < 0) return false;
        int low = 1, high = num;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            long mul = (long) mid * mid;
            if (mul > num) {
                high = mid - 1;
            } else if (mul < num) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test0() {
        int num = 16;
        assertTrue(isPerfectSquare(num));
    }

    @Test
    public void test1() {
        int num = 15;
        assertFalse(isPerfectSquare(num));
    }
    
    @Test
    public void test2() {
        int num = 1;
        assertTrue(isPerfectSquare(num));
    }
    
    @Test
    public void test3() {
        int num = Integer.MAX_VALUE;
        assertFalse(isPerfectSquare(num));
    }
}
