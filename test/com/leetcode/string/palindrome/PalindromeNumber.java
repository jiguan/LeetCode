package com.leetcode.string.palindrome;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

// Determine whether an integer is a palindrome. Do this without extra space.
public class PalindromeNumber {
    public boolean isPalindrome0(int x) {
        // If x is 10, sum will be 0, so as x (10 -> 1 -> 0).
        if (x < 0 || (x != 0 && x % 10 == 0))
            return false;

        int sum = 0;
        while (sum < x) {
            sum = sum * 10 + x % 10;
            x /= 10;
        }
        // If x is odd digits, we need to compare the second last digit of sum
        // with last digit of x. e.g. sum = 12, x = 1.
        return sum == x || x == (sum / 10);
    }

    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;

        int p = x;
        int q = 0;

        while (p > 9) {
            q *= 10;
            q += p % 10;
            p /= 10;
        }

        // q is reconstructed from behind, p is the left-most digit.
        return q == x / 10 && p == x % 10;

    }

    @Test
    public void test0() {
        assertTrue(isPalindrome(121));
    }

    @Test
    public void test1() {
        assertFalse(isPalindrome(10));
    }
}
