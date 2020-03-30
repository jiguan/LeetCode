package com.leetcode.array.stack;

import static org.junit.Assert.assertArrayEquals;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.junit.Test;

public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.add(num);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; ++i) {
            res[i] = map.getOrDefault(nums1[i], -1);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums1 = {1, 3, 5, 2, 4};
        int[] nums2 = {6, 5, 4, 3, 2, 1, 7};
        int[] expected = {7, 7, 7, 7, 7};
        assertArrayEquals(expected, nextGreaterElement(nums1, nums2));
    }
}
