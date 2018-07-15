package com.leetcode.implement;

import java.util.Stack;

import org.junit.Test;

public class MyQueue {

    @Test
    public void test0() {
        MyQueueImpl queue = new MyQueueImpl();
        int[] nums = new int[]{1, 2, 3, 4};
        for (int num : nums) {
            queue.push(num);
        }
        while (!queue.empty()) {
            System.out.print(queue.peek());
            queue.pop();
        }
    }

}

class MyQueueImpl {
    Stack<Integer> input = new Stack<Integer>();
    Stack<Integer> output = new Stack<Integer>();

    public void push(int x) {
        input.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        peek();
        output.pop();
    }

    // Get the front element.
    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }
        return output.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

}
