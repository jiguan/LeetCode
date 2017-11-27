package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; ++i) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum > target) {
                    --end;
                } else if (sum < target) {
                    ++start;
                } else {
                    return target;
                }
                if (Math.abs(res - target) > Math.abs(sum - target)) {
                    res = sum;
                }

            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{0, 2, 1, -3};
        assertEquals(0, threeSumClosest(nums, 1));
    }
}
