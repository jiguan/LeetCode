package com.leetcode.string;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class AdditiveNumber {
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n / 2; ++i) {
            // n - i - j is the sum length, there is no way sum is less than x1 or x2 length
            for (int j = 1; Math.max(i, j) <= n - i - j; ++j) {
                if (check(num, i, j)) return true;
            }
        }
        return false;
    }

    private boolean check(String num, int i, int j) {
        if (num.charAt(0) == '0' && i > 1) return false;
        if (num.charAt(i) == '0' && j > 1) return false;
        String sum;
        BigInteger x1 = new BigInteger(num.substring(0, i));
        BigInteger x2 = new BigInteger(num.substring(i, i + j));
        for (int start = i + j; start < num.length(); start += sum.length()) {
            x2 = x2.add(x1); // sum
            x1 = x2.subtract(x1);
            sum = x2.toString();
            if (!num.startsWith(sum, start)) return false;
        }
        return true;
    }

    @Test
    public void test0() {
        assertTrue(isAdditiveNumber("101"));
    }
}