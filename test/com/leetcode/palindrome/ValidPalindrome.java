package com.leetcode.palindrome;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ValidPalindrome {
    public boolean isPalindrome(String str) {
        int start = 0, end = str.length() - 1;
        char[] arr = str.toCharArray();
        while (start < end) {
            if (!Character.isLetterOrDigit(arr[start])) {
                ++start;
            } else if (!Character.isLetterOrDigit(arr[end])) {
                --end;
            } else {
                if (Character.toLowerCase(arr[start]) != Character.toLowerCase(arr[end])) {
                    return false;
                } else {
                    start++;
                    end--;
                }
            }
        }
        return true;
    }

    @Test
    public void test0() {
        String s = "A man, a plan, a canal: Panama";
        assertTrue(isPalindrome(s));
    }
}
