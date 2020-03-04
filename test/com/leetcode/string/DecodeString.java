package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

public class DecodeString {
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int num = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if (ch == '[') {
                intStack.push(num);
                num = 0;
                strStack.push(curr);
                curr = new StringBuilder();
            } else if (ch == ']') {
                StringBuilder tmp = curr;
                // back to higher level
                curr = strStack.pop();
                int times = intStack.pop();
                for (int i = 0; i < times; ++i) {
                    curr.append(tmp);
                }
            } else {
                curr.append(ch);
            }
        }
        return curr.toString();
    }

    @Test
    public void test0() {
        String s = "3[a]2[bc]";
        String expected = "aaabcbc";
        assertEquals(expected, decodeString(s));
    }

    @Test
    public void test1() {
        String s = "3[a2[c]]";
        String expected = "accaccacc";
        assertEquals(expected, decodeString(s));
    }
}
