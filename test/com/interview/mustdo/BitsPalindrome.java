package com.interview.mustdo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

// Zillow
// check the bit of a num is palindrome or not 
public class BitsPalindrome {
    public boolean isPalindrome(int num) {
        int tmp = num;
        int res = 0;
        while(tmp != 0) {
            res = (res << 1) | (tmp & 1);
            tmp >>>= 1;
        }
        return res == num;
    }

    @Test
    public void test0() {
        int num = 9;
        assertTrue(isPalindrome(num));
    }

    @Test
    public void test1() {
        int num = 21;
        assertTrue(isPalindrome(num));
    }

    @Test
    public void test2() {
        int num = 0;
        assertTrue(isPalindrome(num));
    }

    @Test
    public void test3() {
        int num = 11;
        assertFalse(isPalindrome(num));
    }
    
    @Test
    public void test4() {
        int num = -1;
        assertTrue(isPalindrome(num));
    }
}
