package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class ImplementStackUsingQueues {
    private Queue<Integer> queue = new LinkedList<>();

    // Push element x onto stack.
    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); ++i) {
            queue.add(queue.remove());
        }
    }

    // Removes the element on top of the stack.
    public int pop() {
        return queue.poll();
    }

    // Get the top element.
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }

    @Test
    public void test0() {
        for (int i = 1; i <= 5; i++) {
            push(i);
        }
        for (int i = 5; i > 0; --i) {
            assertTrue(i == top());
            pop();
        }
    }
}
