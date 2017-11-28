package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

public class MinStack {
	Stack<List<Integer>> stack = new Stack<>();
	Integer min = null;
	public void push(int x) {
		List<Integer> node = new ArrayList<>(2);
		node.add(x);
		if(stack.isEmpty()) {
			node.add(x);
		} else {
			node.add(Math.min(x, stack.peek().get(1)));
		}
		stack.push(node);
	}
	
	public void pop() {
		stack.pop();
	}

	public int top() {
		return stack.peek().get(0);
	}

	public int getMin() {
		return stack.peek().get(1);
	}
	
	
	@Test
	public void test0() {
		List<Integer> result = new ArrayList<>();
		MinStack stack = new MinStack();
		stack.push(2);
		stack.push(0);
		stack.push(3);
		stack.push(0);
		result.add(stack.getMin());
		stack.pop();
		result.add(stack.getMin());
		stack.pop();
		result.add(stack.getMin());
		stack.pop();
		result.add(stack.getMin());
		List<Integer> expected = Arrays.asList(0,0,0,2);
		assertEquals(expected, result);
	}
}
