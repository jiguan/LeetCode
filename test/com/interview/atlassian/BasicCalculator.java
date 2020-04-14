package com.interview.atlassian;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

public class BasicCalculator {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int cur = 0;
        // trigger the last operation
        s += '+';

        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (ch == ' ') continue;
            if (Character.isDigit(ch)) {
                cur = cur * 10 + (ch - '0');
            } else {
                // check the last sign
                if (sign == '*') {
                    stack.push(cur * stack.pop());
                } else if (sign == '/') {
                    stack.push(stack.pop() / cur);
                } else if (sign == '-') {
                    stack.push(-cur);
                } else if (sign == '+') {
                    stack.push(cur);
                }
                cur = 0;
                sign = ch;
            }

        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

    @Test
    public void test0() {
        String s = "2*3";
        assertEquals(Integer.valueOf(6), Integer.valueOf(calculate(s)));
    }
}
