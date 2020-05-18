package com.leetcode.array.stack;

import static org.junit.Assert.assertTrue;
import java.util.Stack;
import org.junit.Test;

/*
 * Given two sequences pushed and popped with distinct values, return true if and only if this
 * **COULD BE** the result of a sequence of push and pop operations on an initially empty stack.
 */
public class ValidateStackSequences {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        // index for popped
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        for (int num : pushed) {
            stack.add(num);
            while (!stack.isEmpty() && index < popped.length && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }
        return index == popped.length;
    }

    @Test
    public void test0() {
        int[] pushed = {1, 2, 3, 4, 5}, popped = {4, 5, 3, 2, 1};
        assertTrue(validateStackSequences(pushed, popped));
    }
}
