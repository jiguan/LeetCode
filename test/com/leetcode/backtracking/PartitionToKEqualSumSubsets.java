package com.leetcode.backtracking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % k != 0) return false;

        Arrays.sort(nums);
        for (int i = 0; i < nums.length / 2; ++i) {
            int tmp = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = tmp;
        }

        boolean[] visit = new boolean[nums.length];
        return partition(nums, 0, visit, 0, sum / k, k);
    }

    private boolean partition(int[] nums, int index, boolean[] visit, int sum, int target, int k) {
        if (target == sum) {
            if (k == 1) return true;
            return partition(nums, 0, visit, 0, target, k - 1);
        }

        for (int i = index; i < nums.length && sum < target; ++i) {
            if (visit[i] == false) {
                visit[i] = true;
                if (partition(nums, i + 1, visit, sum + nums[i], target, k)) return true;
                visit[i] = false;
            }
        }
        return false;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        assertTrue(canPartitionKSubsets(nums, k));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{2, 2, 2, 2, 3, 4, 5};
        int k = 4;
        assertFalse(canPartitionKSubsets(nums, k));
    }
}
