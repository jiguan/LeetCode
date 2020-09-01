package com.interview.oci;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CheckIfBinaryRepresentationOfANumberIsPalindrome {
    public boolean isPalindrome(int x) {
        int reverse = 0;
        int tmp = x;
        while (tmp > 0) {
            reverse <<= 1;
            if ((tmp & 1) == 1) {
                reverse ^= 1;
            }
            tmp >>= 1;
        }
        if (x == reverse) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void test0() {
        assertTrue(isPalindrome(5));
    }

    @Test
    public void test1() {
        assertFalse(isPalindrome(13));
    }
}
