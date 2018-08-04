package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// "-4" -> "-4.0", "2/5" -> "0.4", "1 2/5" -> "1.4"
public class StringToFloat {
    public String myAtoi(String str) {
        if (str == null || str.isEmpty()) return "0";
        int index = 0;
        while (index < str.length() && str.charAt(index) == ' ')
            index++;
        StringBuilder builder = new StringBuilder();
        if (index < str.length() && str.charAt(index) == '+') {
            index++;
        } else if (index < str.length() && str.charAt(index) == '-') {
            builder.append("-");
            index++;
        }

        boolean containsDot = false;

        for (int i = index; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (c == '.' || c == ' ') {
                builder.append(".");
                containsDot = true;
                while (i + 1 < str.length() && str.charAt(i + 1) == str.charAt(i))
                    i++;
            } else if (c == '/') {
                index = builder.indexOf(".");
                String upper = builder.substring(index + 1);
                builder.setLength(index + 1);

                StringBuilder builder2 = new StringBuilder();
                while (++i < str.length()) {
                    char c2 = str.charAt(i);
                    if (c2 != ' ') builder2.append(c2);
                }
                String lower = builder2.toString();
                float res = Float.parseFloat(upper) / Float.parseFloat(lower);
                if (res % 1 != 0) containsDot = true;
                builder.append(res);
            } else {
                builder.append(c);
            }
        }
        if (containsDot == false) {
            builder.append(".0");
        }
        return builder.toString();
    }

    @Test
    public void test0() {
        String str = "2/5";
        String expected = "0.4";
        assertEquals(expected, myAtoi(str));
    }
}
