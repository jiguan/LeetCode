package com.leetcode.array.stack;

import static org.junit.Assert.assertArrayEquals;
import java.util.Stack;
import org.junit.Test;

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids == null || asteroids.length == 0 || asteroids.length == 1) return asteroids;

        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            if (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
                while (!stack.isEmpty()
                        && (stack.peek() > 0 && asteroid < 0 && stack.peek() + asteroid < 0)) {
                    stack.pop();
                }
                if (!stack.isEmpty() && (stack.peek() + asteroid == 0)) {
                    stack.pop();
                } else if (stack.isEmpty() || stack.peek() < 0) {
                    stack.add(asteroid);
                }
            } else {
                // stack.peek() > 0 && asteroid > 0
                // stack.peek() < 0 && asteroid < 0
                // stack.peek() < 0 && asteroid > 0
                stack.add(asteroid);
            }
        }


        int[] arr = new int[stack.size()];
        for (int i = arr.length - 1; i >= 0; --i) {
            arr[i] = stack.pop();
        }
        return arr;
    }

    @Test
    public void test0() {
        int[] asteroids = {8, -8};
        int[] expecteds = {};
        assertArrayEquals(expecteds, asteroidCollision(asteroids));
    }

    @Test
    public void test1() {
        int[] asteroids = {10, 2, -5};
        int[] expecteds = {10};
        assertArrayEquals(expecteds, asteroidCollision(asteroids));
    }

    @Test
    public void test2() {
        int[] asteroids = {5, 5, -5};
        int[] expecteds = {5};
        assertArrayEquals(expecteds, asteroidCollision(asteroids));
    }

    @Test
    public void test3() {
        int[] asteroids = {-2, -2, 1, -2};
        int[] expecteds = {-2, -2, -2};
        assertArrayEquals(expecteds, asteroidCollision(asteroids));
    }

}
