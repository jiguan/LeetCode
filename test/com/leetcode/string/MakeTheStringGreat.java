package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * 1544. Make The String Great
 * 
 * Given a string s of lower and upper case English letters.
 * 
 * A good string is a string which doesn't have two adjacent characters s[i] and s[i + 1] where:
 * 
 * 0 <= i <= s.length - 2 s[i] is a lower-case letter and s[i + 1] is the same letter but in
 * upper-case or vice-versa. To make the string good, you can choose two adjacent characters that
 * make the string bad and remove them. You can keep doing this until the string becomes good.
 * 
 * Return the string after making it good. The answer is guaranteed to be unique under the given
 * constraints.
 * 
 * Notice that an empty string is also good.
 */
public class MakeTheStringGreat {
    public String makeGood(String s) {
        char[] res = new char[s.length()];
        char[] arr = s.toCharArray();
        int j = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (j > 0 && Math.abs(arr[i] - res[j - 1]) == 32) {
                --j;
            } else {
                res[j++] = arr[i];
            }
        }
        return new String(res, 0, j);
    }

    @Test
    public void test0() {
        String s = "leEeetcode";
        String expected = "leetcode";
        assertEquals(expected, makeGood(s));
    }

    @Test
    public void test1() {
        String s = "abBAcC";
        String expected = "";
        assertEquals(expected, makeGood(s));
    }
}
