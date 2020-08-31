package com.leetcode.design;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Stack;
import org.junit.Test;

public class ImplementQueueUsingStacks {

    @Test
    public void test0() {
        MyQueue queue = new MyQueue();
        int[] nums = new int[] {1, 2, 3, 4};
        for (int num : nums) {
            queue.push(num);
        }
        for (int i = nums.length - 1; i >= 0; --i) {
            assertEquals(nums[i], queue.peek());
            queue.pop();
        }
        assertTrue(queue.empty());
    }
}

class MyQueue {
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
