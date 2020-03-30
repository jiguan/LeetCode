package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B. Remove
 * the outermost parentheses of every primitive string.
 */
public class RemoveOutermostParentheses {
    public String removeOuterParentheses(String S) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        for (char ch : S.toCharArray()) {
            if (ch == '(') {
                if (open > 0) { // handle "(("
                    sb.append(ch);
                }
                open++;
            } else if (ch == ')') {
                // there is more than one open
                if (open > 1) {
                    sb.append(ch);
                }
                open--;
            }
        }
        return sb.toString();
    }

    @Test
    public void test0() {
        String s = "(()())(())";
        assertEquals("()()()", removeOuterParentheses(s));
    }

    @Test
    public void test1() {
        String s = "()()";
        assertEquals("", removeOuterParentheses(s));
    }

    @Test
    public void test2() {
        String s = "(())";
        assertEquals("()", removeOuterParentheses(s));
    }
}
