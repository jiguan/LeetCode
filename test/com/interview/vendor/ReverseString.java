package com.interview.vendor;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

// Given a string with special chars, reverse the string with keeping special char position as it
// is.
// Given: ab?c/-dhjk!
// Output should be: kj?h/-dcba!
public class ReverseString {
    public String reverse(String str) {
        char[] res = new char[str.length()];
        int i = 0, j = str.length() - 1;
        char[] chs = str.toCharArray();
        while (i <= j) {
            if (!Character.isAlphabetic(chs[i])) {
                res[i] = chs[i];
                ++i;
            } else if (!Character.isAlphabetic(chs[j])) {
                res[j] = chs[j];
                --j;
            } else {
                res[i] = chs[j];
                res[j] = chs[i];
                --j;
                ++i;
            }
        }
        return String.valueOf(res);
    }

    @Test
    public void test0() {
        String str = "ab?c/-dhjk!";
        String expected = "kj?h/-dcba!";
        assertEquals(expected, reverse(str));
    }
}
