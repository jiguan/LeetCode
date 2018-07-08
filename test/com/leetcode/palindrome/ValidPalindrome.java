package com.leetcode.palindrome;

public class ValidPalindrome {
    public boolean isPalindrome(String str) {
        int start = 0, end = str.length() - 1;
        char[] arr = str.toCharArray();
        while (start <= end) {
            if (!Character.isLetterOrDigit(arr[start])) {
                ++start;
            } else if (!Character.isLetterOrDigit(arr[end])) {
                --end;
            } else {
                if (arr[start++] != arr[end--]) {
                    return false;
                }
            }
        }
        return true;
    }
}
