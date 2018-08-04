package com.leetcode.binearysearch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TwentyFourGame2 {
    final double eps = 0.001;
    
    public boolean judgePoint24(int[] nums) {
        return calc(new double[]{nums[0], nums[1], nums[2], nums[3]});
    }

    private boolean calc(double[] nums) {
        if (nums.length == 1) {
            return Math.abs(nums[0] - 24) < eps;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // retrieve nums[i] and nums[j], and save their result
                double[] next = new double[nums.length - 1];
                for (int k = 0, index = 0; k < nums.length; k++) {
                    if (k != i && k != j) {
                        next[index++] = nums[k];
                    }
                }
                for (double k : compute(nums[i], nums[j])) {
                    next[nums.length - 2] = k;
                    if (calc(next)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private double[] compute(double a, double b) {
        return new double[]{a + b, a - b, b - a, a * b, a / b, b / a};
    }

    @Test
    public void test0() {
        int[] nums = new int[]{3, 3, 8, 8};
        assertTrue(judgePoint24(nums));
    }
}
