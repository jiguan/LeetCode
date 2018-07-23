package com.interview.mustdo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringToLong {
    // Question 1) Given a string, write a routine that converts the string to a long, without using the
    // built in functions that would do this. Describe what (if any) limitations the code has
    public long stringToLong(String s) {
        int index = 0;
        while(index < s.length() && s.charAt(index) == ' ') index++;
        int sign = 1;
        if(index < s.length() && s.charAt(index) =='+') {
            index++;
            sign = 1;
        } else if (index < s.length() && s.charAt(index) == '-') {
            index++;
            sign = -1;
        }
        
        long res = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c >= '0' && c <='9') {
                int digit = s.charAt(i) - '0';
                if (sign == 1 && res > (Long.MAX_VALUE - digit) / 10) {
                    return Long.MAX_VALUE;
                } else if (sign == -1 && res < (Long.MIN_VALUE + digit) / 10) {
                    return Long.MIN_VALUE;
                }
                res = res * 10 + sign * digit;
            }
        }
        return res;
    }

    @Test
    public void testSpecialCharacter() {
        String s = "    +123,456,789";
        assertTrue(123456789L == stringToLong(s));
    }
    
    @Test
    public void testSpecialCharacter2() {
        String s = "    -123,456,789";
        assertTrue(-123456789L == stringToLong(s));
    }

    @Test
    public void testMinVal() {
        String s = String.valueOf(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, stringToLong(s));
    }

    @Test
    public void testMaxVal() {
        String s = String.valueOf(Long.MAX_VALUE);
        assertEquals(Long.MAX_VALUE, stringToLong(s));
    }

    @Test
    public void testMaxOverStack() {
        String s = String.valueOf(Long.MAX_VALUE);
        s = s.substring(0, s.length() - 1) + ((s.charAt(s.length() - 1) - '0') + 1);
        assertEquals(Long.MAX_VALUE, stringToLong(s));
    }

    @Test
    public void testMinOverStack() {
        String s = String.valueOf(Long.MIN_VALUE);
        s = s.substring(0, s.length() - 1) + ((s.charAt(s.length() - 1) - '0') + 1);
        assertEquals(Long.MIN_VALUE, stringToLong(s));
    }
}
