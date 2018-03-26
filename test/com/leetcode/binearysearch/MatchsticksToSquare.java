package com.leetcode.binearysearch;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class MatchsticksToSquare {
    public boolean makesquare(int[] nums) {
        Arrays.sort(nums);
        // Descending order to that sum judge condition could fail earlier
        for (int i = 0; i < nums.length / 2; ++i) {
            int tmp = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = tmp;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) return false;

        return dfs(nums, new int[4], 0, sum / 4);
    }

    private boolean dfs(int[] nums, int[] sum, int index, int target) {
        if (index == nums.length) {
            // since target = sum / 4, first three are equals to target, then
            // the last one must be
            if (sum[0] == target && sum[1] == target && sum[2] == target) {
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; ++i) {
            if (sum[i] + nums[index] > target) continue;
            sum[i] += nums[index];
            if (dfs(nums, sum, index + 1, target)) return true;
            sum[i] -= nums[index];
        }

        return false;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 1, 2, 2, 2};
        assertTrue(makesquare(nums));
    }
    
    @Test
    public void test1() {
        int[] nums = new int[]{12,18,2,2,16,8,7,3,10,12,3,20,2,10,19};
        assertTrue(makesquare(nums));
    }
}
