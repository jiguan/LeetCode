package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SqrtX {
    public int mySqrt(int x) {
        if (x <= 1) return x;
        int left = 1, right = x;
        // last step, left = mid = right.
        while (left <= right) {
            int mid = (right - left >> 1) + left;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                // mid < sqrt(x)
                left = mid + 1;
            } else {
                // mid > sqrt(x)
                right = mid - 1;
            }
        }
        // finally, left is larger than sqrt(x)
        return right;
    }

    public int mySqrt1(int x) {
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int) r;
    }
    
    @Test
    public void test0() {
        int x = 9;
        assertEquals(3, mySqrt(x));
    }

    @Test
    public void test1() {
        int x = 25;
        assertEquals(5, mySqrt(x));
    }
    
    @Test
    public void test2() {
        int x = 8;
        assertEquals(2, mySqrt(x));
    }
}