package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

// The expression string contains only non-negative integers, +, -, *, / operators and empty spaces

public class BasicCalculatorII {
    public int calculate(String s) {
        if(s.length()==0) return 0;
        s += " ";
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        for(int i=0;i<s.length();i++) {
            if(Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            } else if(s.charAt(i)!=' '||i==s.length()-1) {
                if(sign=='-') {
                    stack.push(-num);
                } else if(sign=='+') {
                    stack.push(num);
                } else if(sign=='*') {
                    stack.push(stack.pop()* num);
                } else {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        for(int i : stack) {
            num += i;
        }
        return num;
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
