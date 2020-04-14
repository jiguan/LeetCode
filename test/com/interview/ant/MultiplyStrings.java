package com.interview.ant;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MultiplyStrings {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];

        for (int i = num1.length() - 1; i >= 0; --i) {
            for (int j = num2.length() - 1; j >= 0; --j) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                int sum = res[i + j + 1] + mul;
                res[i + j] += sum / 10;
                res[i + j + 1] = sum % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            if (!(sb.length() == 0 && num == 0)) sb.append(num);
        }
        if (sb.length() == 0) return "0";
        return sb.toString();
    }

    @Test
    public void test0() {
        String num1 = "123", num2 = "45";
        assertEquals(String.valueOf(123 * 45), multiply(num1, num2));
    }
}
