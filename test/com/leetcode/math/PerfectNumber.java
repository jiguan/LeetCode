package com.leetcode.math;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PerfectNumber {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) return false;

        int sum = 0;
        for (int i = 2; i <= Math.sqrt(num); ++i) {
            if (num % i == 0) {
                sum += num / i + i;
            }
        }
        sum += 1;
        return sum == num;
    }

    @Test
    public void test0() {
        int num = 28;
        assertTrue(checkPerfectNumber(num));
    }
}
