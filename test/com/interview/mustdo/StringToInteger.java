package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringToInteger {
    public int myAtoi(String str) {
        if (str == null || str.isEmpty()) return 0;

        int index = 0;
        int sign = 1, res = 0;
        while (index < str.length() && str.charAt(index) == ' ')
            index++;
        if (index < str.length() && str.charAt(index) == '-') {
            sign = -1;
            index++;
        } else if (index < str.length() && str.charAt(index) == '+') {
            sign = 1;
            index++;
        }

        for (int i = index; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                if (sign == 1 && (Integer.MAX_VALUE - (c - '0')) / 10 < res) {
                    return Integer.MAX_VALUE;
                } else if (sign == -1 && (Integer.MIN_VALUE + (c - '0')) / 10 > res) {
                    return Integer.MIN_VALUE;
                } else {
                    res = res * 10 + sign * (c - '0');
                }
            } else
                break;
        }
        return res;
    }

    @Test
    public void test0() {
        String str = "+-2";
        assertEquals(0, myAtoi(str));
    }

    @Test
    public void test1() {
        String str = "-3.14";
        assertEquals(-3, myAtoi(str));
    }

    @Test
    public void test2() {
        String str = "4193 with words";
        assertEquals(4193, myAtoi(str));
    }

    @Test
    public void test3() {
        String str = "4193 with words";
        assertEquals(4193, myAtoi(str));
    }

    @Test
    public void test4() {
        String str = " ";
        assertEquals(0, myAtoi(str));
    }

    @Test
    public void test5() {
        String str = "2147483648";
        assertEquals(2147483647, myAtoi(str));
    }
}
