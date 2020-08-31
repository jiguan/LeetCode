package com.leetcode.design;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

public class MinStackUsingStack {
    Integer min = Integer.MAX_VALUE;
    List<Integer> list = new LinkedList<>();

    public void push(int x) {
        if (min >= x) {
            list.add(min);
            min = x;
        }
        list.add(x);
    }

    public void pop() {
        if (list.isEmpty()) return;
        if (list.remove(list.size() - 1) == min) {
            min = list.remove(list.size() - 1);
        }
    }

    public int top() {
        if (list.isEmpty()) return -1;
        return list.get(list.size() - 1);
    }

    public int getMin() {
        return min != null ? min : -1;
    }

    @Override
    public String toString() {
        return list.toString() + ", min=" + min;
    }

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
