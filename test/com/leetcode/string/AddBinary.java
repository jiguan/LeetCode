package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = (i >= 0 ? a.charAt(i--) - '0' : 0) + (j >= 0 ? b.charAt(j--) - '0' : 0) + carry;
            sb.insert(0, sum % 2);
            carry = sum / 2;
        }
        return sb.toString();
    }

    @Test
    public void test0() {
        String a = "11", b = "1";
        assertEquals("100", addBinary(a, b));
    }

    @Test
    public void test1() {
        String a = "10001", b = "1";
        assertEquals("10010", addBinary(a, b));
    }

    @Test
    public void test2() {
        String a = "", b = "";
        assertEquals("", addBinary(a, b));
    }

    @Test
    public void test3() {
        String a = "1", b = "111";
        assertEquals("1000", addBinary(a, b));
    }
}
