package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

public class ScoreOfParentheses {
    public int scoreOfParentheses(String S) {
        char[] chs = S.toCharArray();
        int res = 0, open = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '(') {
                open++;
            } else {
                open--;
                // find the deepest pair
                if (chs[i - 1] == '(') {
                    res += 1 << open;
                }
            }
        }
        return res;
    }

    public int scoreOfParenthesesUsingStack(String S) {
        // only store 2 levels
        Stack<Integer> stack = new Stack<>();
        int cur = 0;
        for (char c : S.toCharArray()) {
            if (c == '(') {
                stack.push(cur);
                cur = 0;
            } else {
                cur = stack.pop() + Math.max(cur * 2, 1);
            }
        }
        return cur;
    }


    @Test
    public void test0() {
        String S = "()";
        assertEquals(Integer.valueOf(1), Integer.valueOf(scoreOfParentheses(S)));
    }

    @Test
    public void test1() {
        String S = "(())";
        assertEquals(Integer.valueOf(2), Integer.valueOf(scoreOfParentheses(S)));
    }

    @Test
    public void test2() {
        String S = "(()(()))";
        assertEquals(Integer.valueOf(6), Integer.valueOf(scoreOfParentheses(S)));
    }
}
