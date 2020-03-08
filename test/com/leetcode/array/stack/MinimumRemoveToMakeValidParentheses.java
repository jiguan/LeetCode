package com.leetcode.array.stack;

import static org.junit.Assert.assertTrue;
import java.util.Stack;
import org.junit.Test;

public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); ++i) {
            if (sb.charAt(i) == '(') {
                stack.push(i);
            } else if (sb.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    sb.setCharAt(i, '*');
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            sb.setCharAt(stack.pop(), '*');
        }
        return sb.toString().replaceAll("\\*", "");
    }

    @Test
    public void test0() {
        String s = "lee(t(c)o)de)";
        // "lee(t(co)de)" , "lee(t(c)ode)"
        String expected1 = "lee(t(c)o)de";
        String expected2 = "lee(t(c)ode)";

        String actual = minRemoveToMakeValid(s);
        assertTrue(expected1.equals(actual) || expected2.equals(actual));
    }
}
