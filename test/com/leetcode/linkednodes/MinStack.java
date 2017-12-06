package com.leetcode.linkednodes;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class MinStack {
    private int min = Integer.MAX_VALUE;
    private Stack<Integer> stack = new Stack<>();

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        // push twice for min
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        // pop twice for min
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    @Test
    public void test0() {
        MinStack stack = new MinStack();
        stack.push(0);
        stack.push(1);
        stack.push(0);

        assertEquals(0, stack.getMin());
        stack.pop();
        assertEquals(0, stack.getMin());
    }
}
