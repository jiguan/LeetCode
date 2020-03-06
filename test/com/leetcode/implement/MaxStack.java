package com.leetcode.implement;

import static org.junit.Assert.assertEquals;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import org.junit.Test;

public class MaxStack {
    PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> (b - a));
    // use deque so that we could call function to check from the tail to head
    Deque<Integer> queue = new ArrayDeque<>();

    public void push(int x) {
        maxQueue.offer(x);
        queue.addLast(x);
    }

    public int pop() {
        int val = queue.removeLast();
        maxQueue.remove(val);
        return val;
    }

    public int top() {
        return queue.getLast();
    }

    public int peekMax() {
        return maxQueue.peek();
    }

    public int popMax() {
        int res = maxQueue.poll();
        queue.removeLastOccurrence(res);
        return res;
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
