package com.interview.karat;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import org.junit.Test;

public class BasicCalculator {
    public int calculate1(String s) {
        int cur = 0;
        int res = 0;
        int sign = 1;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                cur = cur * 10 + ch - '0';
            } else if (ch == '+' || ch == '-') {
                res += sign * cur;
                cur = 0;
                sign = ch == '+' ? 1 : -1;
            }
        }
        if (cur != 0) res += sign * cur;
        return res;
    }

    public int calculate2(String s) {
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        int cur = 0;
        int res = 0;
        for (char ch : s.toCharArray()) {
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
    
    public String calculate3(String s, Map<String, Integer> map) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        String res = "";
        Deque<Integer> signStack = new LinkedList<>();
        int sign = 1;
        // default to be '+'
        signStack.push(1);
        List<String> notMappedVariables = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                signStack.push(sign * signStack.peek());
                sign = 1;
            } else if (ch == ')') {
                signStack.pop();
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (Character.isDigit(ch)) {
                int num = ch - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(++i) - '0';
                }
                String prefix = sign * signStack.peek() == 1 ? "+":"-";
                res += prefix + num;
            } else if (Character.isAlphabetic(ch)) {
                String v = "" + ch;
                while (i + 1 < s.length() && Character.isAlphabetic(s.charAt(i + 1))) {
                    v += s.charAt(++i);
                }
                if (map.containsKey(v)) {
                    v = "" + map.get(v);
                    String prefix = sign * signStack.peek() == 1 ? "+":"-";
                    res += prefix + v;
                } else {
                    String variableString = (signStack.peek() * sign == 1 ? "+" : "-") + v;
                    notMappedVariables.add(variableString);
                }
            }
        }
        System.out.println(notMappedVariables);
        res = "" + calculate1(res);
        for (String each : notMappedVariables) {
            res += each;
        }
        return res;
   }

    @Test
    public void test0() {
        String s = "(1+(4+5+2)-3)+(6+8)";
        assertEquals(23, calculate2(s));
    }

    @Test
    public void test1() {
        String s = " (1 +(4+15+2)-13)+ (6+8)";
        assertEquals(23, calculate2(s));
    }

    @Test
    public void test2() {
        String s = "1";
        assertEquals(1, calculate2(s));
    }

    @Test
    public void test3() {
        String s = "A-2-(B+C)-3";
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("A", 3);
        assertEquals("-2-B-C", calculate3(s, map));
    }
}
