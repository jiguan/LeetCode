package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReachANumber {
    public int reachNumber(int target) {
        target = Math.abs(target);

        int sum = 0;
        int steps = 0;
        while (sum < target) {
            steps++;
            sum += steps;
        }

        while ((sum - target) % 2 != 0) {
            sum += ++steps;
        }
        return steps;
    }

    @Test
    public void test0() {
        int target = 3;
        assertEquals(Integer.valueOf(2), Integer.valueOf(reachNumber(target)));
    }

    @Test
    public void test2() {
        int target = 4;
        assertEquals(Integer.valueOf(3), Integer.valueOf(reachNumber(target)));
    }

    @Test
    public void test1() {
        int target = -2;
        assertEquals(Integer.valueOf(3), Integer.valueOf(reachNumber(target)));
    }
}
