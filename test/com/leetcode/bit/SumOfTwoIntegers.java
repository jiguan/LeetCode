package com.leetcode.bit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SumOfTwoIntegers {
    public int getSum(int a, int b) {
        if (a == 0) return b;
        while (b != 0) {
            int carry = a & b; // find 1 bit position in a and b
            a = a ^ b; // reset 1 in a and b to 0
            b = carry << 1; // advance carry
        }
        return a;
    }

    @Test
    public void test0() {
        int a = 3, b = 5;
        assertEquals(8, getSum(a, b));
    }
}
