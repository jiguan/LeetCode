package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

// The expression string contains only non-negative integers, +, -, *, / operators and empty spaces

public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int cur = 0;
        s += '+';

        for (char ch : s.toCharArray()) {
            if (ch == ' ') continue;
            if (Character.isDigit(ch)) {
                cur = cur * 10 + (ch - '0');
            } else {
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
        String s = " 3+5 / 2 ";
        assertEquals(5, calculate(s));
    }

    @Test
    public void test1() {
        String s = " 0 ";
        assertEquals(0, calculate(s));
    }

    @Test
    public void test2() {
        String s = "0-123";
        assertEquals(-123, calculate(s));
    }
}
