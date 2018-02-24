package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        int res = 0;
        if (nums.length < 3) return 0;
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    // num larger than nums[left] meets the condition as well
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }

        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{2, 2, 3, 4};
        int expected = 3;
        assertEquals(Integer.valueOf(expected), Integer.valueOf(triangleNumber(nums)));
    }
}
