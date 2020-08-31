package com.leetcode.design;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class MinStackOOB {
    @Test
    public void test1() {
        MinStackUsingStack stack = new MinStackUsingStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        assertEquals(-3, stack.getMin());
        stack.pop();
        assertEquals(0, stack.top());
        assertEquals(-2, stack.getMin());
    }

    @Test
    public void test2() {
        MinStackUsingStack stack = new MinStackUsingStack();
        stack.push(1);
        stack.push(-2);
        stack.push(-2);
        stack.push(1);
        stack.pop();
        assertEquals(-2, stack.getMin());
        stack.pop();
        assertEquals(-2, stack.getMin());
        stack.pop();
        assertEquals(1, stack.getMin());
    }

    @Test
    public void test0() {
        List<Integer> result = new ArrayList<>();
        MinStackUsingStack stack = new MinStackUsingStack();
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
        List<Integer> expected = Arrays.asList(0, 0, 0, 2);
        assertEquals(expected, result);
    }
}


class MinStack {

    Node tail = null;

    public void push(int x) {
        if (tail == null) {
            tail = new Node(x, x);
        } else {
            Node node = new Node(x, Math.min(x, tail.min));
            node.prev = tail;
            tail = node;
        }
    }

    public void pop() {
        if (tail != null) {
            tail = tail.prev;
        }
    }

    public int top() {
        return tail == null ? -1 : tail.val;
    }

    public int getMin() {
        return tail == null ? -1 : tail.min;
    }
}


class Node {
    int val;
    int min;
    Node prev;

    Node(int val, int min) {
        this.val = val;
        this.min = min;
    }
}
