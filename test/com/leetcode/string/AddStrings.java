package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AddStrings {
    public String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder(num1);
        char[] n1 = builder.reverse().toString().toCharArray();
        builder = new StringBuilder(num2);
        char[] n2 = builder.reverse().toString().toCharArray();

        int i = 0, carryOver = 0;
        builder = new StringBuilder();
        while (i < n1.length || i < n2.length || carryOver != 0) {
            int a = i >= n1.length ? 0 : n1[i] - '0';
            int b = i >= n2.length ? 0 : n2[i] - '0';
            int sum = a + b + carryOver;
            builder.append(sum % 10);
            carryOver = sum / 10;
            i++;
        }
        return builder.reverse().toString();
    }

    @Test
    public void test0() {
        String num1 = "999", num2 = "3";
        String expected = "1002";
        assertEquals(expected, addStrings(num1, num2));
    }
}
