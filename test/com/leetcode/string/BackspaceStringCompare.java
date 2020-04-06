package com.leetcode.string;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1, j = T.length() - 1;
        while (i >= 0 || j >= 0) {
            i = nextIndex(S, i);
            j = nextIndex(T, j);
            // compare the first valid character
            if (i >= 0 && j >= 0 && S.charAt(i) == T.charAt(j)) {
                --i;
                --j;
            } else {
                break;
            }
        }
        return i == -1 && j == -1;
    }

    private int nextIndex(String str, int index) {
        // back space count
        int back = 0;
        while (index >= 0 && (back > 0 || str.charAt(index) == '#')) {
            back += str.charAt(index) == '#' ? 1 : -1;
            --index;
        }
        return index;
    }

    @Test
    public void test0() {
        String S = "abc###d", T = "da#b#c#";
        assertTrue(backspaceCompare(S, T));
    }

    @Test
    public void test1() {
        String S = "abc###d", T = "da#b#c#";
        assertTrue(backspaceCompare(S, T));
    }

    @Test
    public void test2() {
        String S = "a##c", T = "#a#c";
        assertTrue(backspaceCompare(S, T));
    }
}
