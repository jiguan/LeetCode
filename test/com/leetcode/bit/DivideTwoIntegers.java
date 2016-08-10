package com.leetcode.bit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        int res = 0;
        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        long dvd = Math.abs((long) dividend);
        //remain unchanged
        long dvs = Math.abs((long) divisor);

        while (dvs <= dvd) {
            long temp = dvs, mul = 1;
            //if dvd is smaller than tmp * 2
            while (dvd >= temp << 1) {
                temp <<= 1;
                mul <<= 1;
            }
            dvd -= temp;
            res += mul;
        }
        return sign == 1 ? res : -res;
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
