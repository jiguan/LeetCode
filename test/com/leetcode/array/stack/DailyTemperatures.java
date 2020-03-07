package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        // If there is any elements, the order is descending
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; ++i) {
            // If encounter a large element, fill the res for all elements smaller the this one
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int index = stack.pop();
                res[index] = i - index;
            }
            // The last element is always 0
            stack.push(i);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] temperatures = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        int[] expected = new int[]{1, 1, 4, 2, 1, 1, 0, 0};
        int[] actual = dailyTemperatures(temperatures);
        for (int i = 0; i < temperatures.length; ++i) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
