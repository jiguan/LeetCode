package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String token : tokens) {
			if (token.equals("+")) {
				int num = stack.pop() + stack.pop();
				stack.push(num);
			} else if (token.equals("-")) {
				int num1 = stack.pop(), num2 = stack.pop();
				int num = num2 - num1;
				stack.push(num);
			} else if (token.equals("*")) {
				int num = stack.pop() * stack.pop();
				stack.push(num);
			} else if (token.equals("/")) {
				int num1 = stack.pop(), num2 = stack.pop();
				int num = num2 / num1;
				stack.push(num);
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
	}

	@Test
	public void test0() {
		assertEquals(9, evalRPN(new String[] { "2", "1", "+", "3", "*" }));
		assertEquals(6, evalRPN(new String[] { "4", "13", "5", "/", "+" }));
	}
}
