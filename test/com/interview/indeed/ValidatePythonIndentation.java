package com.interview.indeed;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Stack;
import org.junit.Test;

public class ValidatePythonIndentation {
    // if not valid, output the first line number
    public boolean validate(String[] lines) {
        Stack<Integer> stack = new Stack<>();
        boolean colon = false; 
        for (int i = 0; i < lines.length; ++i) {
            String line = lines[i];
            int curr = getIndent(line);
            if (stack.isEmpty()) {
                if (curr != 0) {
                    System.out.println(line);
                    return false;
                }
            } else if (colon) {
                if (curr <= stack.peek()) {
                    System.out.println(line);
                    return false;
                }
            } else {
                // exit code block
                while (!stack.isEmpty() && stack.peek() > curr) {
                    stack.pop();
                }
                if (stack.peek() != curr) {
                    System.out.println(line);
                    return false;
                }
            }
            colon = line.charAt(line.length() - 1) == ':';
            stack.push(curr);
        }
        return !colon;
    }

    public int getIndent(String line) {
        int res = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                res++;
            } else
                break;
        }
        return res;
    }


    @Test
    public void test0() {
        // @formatter:off
        String[] lines = {
                "def:",
                " abc:",
                "  b2c:",
                "   cc",
                " b5c",
                "b6c"
        };
        // @formatter:on
        assertTrue(validate(lines));
    }
    
    @Test
    public void test1() {
        // @formatter:off
        String[] lines = {
                "def:",
                "abc:"
        };
        // @formatter:on
        assertFalse(validate(lines));
    }
    
    @Test
    public void test2() {
        // @formatter:off
        String[] lines = {
                ":"
        };
        // @formatter:on
        assertFalse(validate(lines));
    }
    
    @Test
    public void test3() {
        // @formatter:off
        String[] lines = {
                "abc:",
                " d",
                "  e"
        };
        // @formatter:on
        assertFalse(validate(lines));
    }
}
