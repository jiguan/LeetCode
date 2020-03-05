package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

public class BasicCalculator_Iterative {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int cur = 0;
        int res = 0;
        for(char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                cur = cur * 10 + ch - '0';
            } else if (ch == '(') {
                stack.push(res);
                stack.push(sign);
                sign = 1;
                res = 0;
            } else if (ch == ')') {
                res += sign * cur;
                cur = 0;
                res *= stack.pop();
                res += stack.pop();
            } else if (ch == '+' || ch == '-') {
                res += sign * cur;
                cur = 0;
                sign = ch == '+' ? 1 : -1;
            }
        }
        if (cur != 0) res += sign * cur;
        return res;
    }

    @Test
    public void test0() {
        String s = "(1+(4+5+2)-3)+(6+8)";
        assertEquals(23, calculate(s));
    }

    @Test
    public void test1() {
        String s = " (1 +(4+15+2)-13)+ (6+8)";
        assertEquals(23, calculate(s));
    }

    @Test
    public void test2() {
        String s = "1";
        assertEquals(1, calculate(s));
    }
}
