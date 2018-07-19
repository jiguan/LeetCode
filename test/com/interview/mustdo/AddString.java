package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddString {
    public String addStrings(String num1, String num2) {
        boolean sign1 = true, sign2 = true;
        if (num1.charAt(0) == '-') {
            sign1 = false;
            num1 = num1.substring(1);
        }
        if (num2.charAt(0) == '-') {
            sign2 = false;
            num2 = num2.substring(1);
        }
        int len1 = num1.length(), len2 = num2.length();
        if (sign1 ^ sign2) {
            if (len1 < len2) {
                return (sign2 ? "" : "-") + minus(num2, num1);
            } else {
                return (sign1 ? "" : "-") + minus(num1, num2);
            }
        } else {
            return (sign1 ? "" : "-") + add(num1, num2);
        }
    }

    public String add(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = len1 - 1, j = len2 - 1; i >= 0 || j >= 0 || carry != 0; --i, --j) {
            int res = carry + (i < 0 ? 0 : num1.charAt(i) - '0') + (j < 0 ? 0 : num2.charAt(j) - '0');
            carry = res / 10;
            builder.append(res % 10);
        }
        return builder.reverse().toString();
    }

    public String minus(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();

        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = len1 - 1, j = len2 - 1; i >= 0 || j >= 0; --i, --j) {
            int d1 = carry + (i < 0 ? 0 : num1.charAt(i) - '0'), d2 = (j < 0 ? 0 : num2.charAt(j) - '0');
            if (d1 < d2) {
                carry = -1;
                d1 = d1 + 10;
            } else {
                carry = 0;
            }
            int res = d1 - d2;
            builder.append(res % 10);
        }
        builder.reverse();
        while (builder.length() > 1 && builder.charAt(0) == '0') {
            builder.deleteCharAt(0);
        }
        return builder.toString();
    }

    @Test
    public void test0() {
        String num1 = "2123", num2 = "98765";
        assertEquals("100888", addStrings(num1, num2));
    }

    @Test
    public void test1() {
        String num1 = "0", num2 = "0";
        assertEquals("0", addStrings(num1, num2));
    }

    @Test
    public void test2() {
        String num1 = "999", num2 = "-999";
        assertEquals("0", addStrings(num1, num2));
    }
    
    @Test
    public void test3() {
        String num1 = "-1000", num2 = "999";
        assertEquals("-1", addStrings(num1, num2));
    }
    
    @Test
    public void test4() {
        String num1 = "-1000", num2 = "-999";
        assertEquals("-1999", addStrings(num1, num2));
    }
}
