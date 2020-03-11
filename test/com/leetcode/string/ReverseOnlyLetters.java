package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ReverseOnlyLetters {
    public String reverseOnlyLetters(String S) {
        char[] arr = S.toCharArray();
        int start = 0, end = arr.length - 1;
        while (start < end) {
            if (!Character.isAlphabetic(arr[start]))
                start++;
            else if (!Character.isAlphabetic(arr[end]))
                end--;
            else {
                char tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
                start++;
                end--;
            }
        }
        return new String(arr);
    }

    @Test
    public void test0() {
        String input = "Test1ng-Leet=code-Q!";
        String expected = "Qedo1ct-eeLg=ntse-T!";
        assertEquals(expected, reverseOnlyLetters(input));
    }
}
