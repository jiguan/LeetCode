package com.leetcode.array;

import java.util.Stack;

import org.junit.Test;

public class ImplementQueueUsingStacks {
    Stack<Integer> input = new Stack<Integer>();
    Stack<Integer> output = new Stack<Integer>();

    public void push(int x) {
        input.push(x);
    }

    // Removes the element from in front of queue.
    public int pop() {
        peek();
        return output.pop();
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

    @Test
    public void test0() {
        ImplementQueueUsingStacks queue = new ImplementQueueUsingStacks();
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

class MyQueue1 {
    Stack<Integer> main = new Stack<Integer>();
    Stack<Integer> backup = new Stack<Integer>();

    public void push(int x) {
        backup.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        check();
        main.pop();
    }

    // Get the front element.
    public int peek() {
        check();
        return main.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return main.isEmpty() && backup.isEmpty();
    }

    private void check() {
        if (main.isEmpty()) {
            while (!backup.isEmpty()) {
                main.push(backup.pop());
            }
        }
    }

}
