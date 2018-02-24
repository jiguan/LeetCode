package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MinimumMovesToEqualArrayElements {
    // minNum is the min element in nums
    // x finally all elements are x
    // m steps to make all elements reach x
    // len nums's length
    // sum sum of the initial array
    // sum + m * (len - 1) == x * len
    // x = minNum + m, every step minNum gets 1
    // sum - minNum * len == m
    public int minMoves(int[] nums) {
        int sum = 0, minNum = Integer.MAX_VALUE;
        for (int num : nums) {
            minNum = Math.min(minNum, num);
            sum += num;
        }
        return sum - minNum * nums.length;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 3};
        assertEquals(Integer.valueOf(3), Integer.valueOf(minMoves(nums)));
    }
}
