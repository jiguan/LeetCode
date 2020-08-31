package com.leetcode.bit;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

// Divide Two Integers
public class DivideTwoIntegers {
    // O(logN^2)
    // There is binary search version
    public int divide0(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        int res = 0;
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long ldividend = Math.abs((long) dividend);
        // remain unchanged
        long ldivisor = Math.abs((long) divisor);

        while (ldividend >= ldivisor) {
            long temp = ldivisor, mul = 1;
            // if dvd is smaller than tmp * 2
            while (ldividend >= (temp << 1)) {
                temp <<= 1;
                mul <<= 1;
            }
            ldividend -= temp;
            res += mul;
        }
        return sign == 1 ? res : -res;
    }

    // O(32)
    public int divide(int A, int B) {
        if (A == 1 << 31 && B == -1) {
            return (1 << 31) - 1;
        }
        int a = Math.abs(A), b = Math.abs(B), res = 0;
        for (int x = 31; x >= 0; x--)
            if ((a >>> x) - b >= 0) {
                res += 1 << x;
                a -= b << x;
            }
        return (A > 0) == (B > 0) ? res : -res;
    }

    @Test
    public void test0() {
        assertTrue((5 / 2) == divide(5, 2));
    }

    @Test
    public void test1() {
        assertTrue((31 / 2) == divide(31, 2));
    }
}
