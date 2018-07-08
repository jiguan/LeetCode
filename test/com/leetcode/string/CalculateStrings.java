package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculateStrings {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; --i) {
            for (int j = num2.length() - 1; j >= 0; --j) {
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[i + j + 1];
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int num : res) {
            if (sb.length() == 0 && num == 0) continue;
            sb.append(num);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
    
    public String plus(String num1, String num2) {
        int carry = 0;
        StringBuffer sb = new StringBuffer();
        int i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 || j >= 0 || carry > 0) {
            int num = (i >= 0 ? num1.charAt(i--) - '0' : 0) + (j >= 0 ? num2.charAt(j--) - '0' : 0) + carry;
            sb.insert(0, num % 10);
            carry = num / 10;
        }
        return sb.toString();
    }

    @Test
    public void test0() {
        String num1 = "123", num2 = "1";
        assertEquals("123", multiply(num1, num2));
    }

    @Test
    public void test2() {
        String num1 = "123", num2 = "456";
        assertEquals("56088", multiply(num1, num2));
    }
    
    @Test
    public void test3() {
        String num1 = "123", num2 = "1";
        assertEquals("124", plus(num1, num2));
    }

    @Test
    public void test4() {
        String num1 = "545", num2 = "456";
        assertEquals("1001", plus(num1, num2));
    }


}
