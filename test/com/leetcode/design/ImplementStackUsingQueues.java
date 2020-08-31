package com.leetcode.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.LinkedList;
import java.util.Queue;
import org.junit.Test;

public class ImplementStackUsingQueues {

    @Test
    public void test0() {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

        assertEquals(4, stack.top());
        stack.pop();
        assertEquals(3, stack.top());
        stack.pop();
        assertEquals(2, stack.top());
        stack.pop();
        assertEquals(1, stack.top());
        stack.pop();
        assertTrue(stack.empty());
    }

    private class MyStack {

        Queue<Integer> queue = new LinkedList<>();
        // define as class member to avoid recreate the object
        Queue<Integer> second = new LinkedList<>();

        /** Push element x onto stack. */
        public void push(int x) {
            second.add(x);
            while (!queue.isEmpty()) {
                second.add(queue.remove());
            }
            Queue<Integer> tmp = queue;
            queue = second;
            second = tmp;
        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue.remove();
        }

        /** Get the top element. */
        public int top() {
            return queue.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

}

