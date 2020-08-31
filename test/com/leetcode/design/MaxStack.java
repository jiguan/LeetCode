package com.leetcode.design;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

public class MaxStack {
    Stack<Integer> stack = new Stack<Integer>();
    Stack<Integer> maxStack = new Stack<Integer>();

    public void push(int x) {
        pushHelper(x);
    }

    private void pushHelper(int x) {
        int tmpMax = maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
        if (tmpMax < x) {
            tmpMax = x;
        }
        stack.push(x);
        maxStack.push(tmpMax);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> tmp = new Stack<Integer>();
        while (stack.peek() != max) {
            tmp.push(stack.pop());
            maxStack.pop();
        }
        // now stack.peek() == max, pop it
        stack.pop();
        maxStack.pop();

        while (!tmp.isEmpty()) {
            pushHelper(tmp.pop());
        }
        return max;
    }

    @Test
    public void test0() {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        stack.push(5);
        assertEquals(5, stack.top());
        assertEquals(5, stack.popMax());
        assertEquals(1, stack.top());
        assertEquals(5, stack.peekMax());
        assertEquals(1, stack.pop());
        assertEquals(5, stack.top());
    }

    @Test
    public void test1() {
        MaxStack stack = new MaxStack();
        stack.push(5);
        stack.push(1);
        assertEquals(5, stack.popMax());
        assertEquals(1, stack.peekMax());
    }
}
