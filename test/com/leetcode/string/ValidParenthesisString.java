package com.leetcode.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidParenthesisString {
    // low : take ¡®*¡¯ as ¡®)¡¯, if there are some ¡®(¡¯ not matched
    // high : take ¡®*¡¯ as ¡®(¡¯
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                if (low > 0) {
                    low--;
                }
                high--;
            } else {
                if (low > 0) {
                    low--;
                }
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }

    @Test
    public void test0() {
        String s = "(*)";
        assertTrue(checkValidString(s));
    }

    @Test
    public void test1() {
        String s = "((*)";
        assertTrue(checkValidString(s));
    }

    @Test
    public void test2() {
        String s = "())*(";
        assertFalse(checkValidString(s));
    }

    @Test
    public void test3() {
        String s = "(";
        assertFalse(checkValidString(s));
    }

    @Test
    public void test4() {
        String s = "(((******))";
        assertTrue(checkValidString(s));
    }

    @Test
    public void test5() {
        String s = "((*)(*()(())())())()()((()())((()))(*";
        assertFalse(checkValidString(s));
    }
}
