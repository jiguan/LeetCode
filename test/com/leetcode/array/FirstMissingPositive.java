package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FirstMissingPositive {
    // Put each number in its right place so that A[0] = 1, A[1] = 2
    // If there is A[num - 1] != num, then we find the first missing positive
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // need to make sure nums[num - 1] == num, e.g [1, 2, ..]
            // note, always use nums[i] to retrieve the current value
            while (nums[i]> 0 && nums[i] <= len && nums[nums[i] - 1] != nums[i]) {
                // although we don't find the right number for the index i, at least we find one for the index num - 1
                // A[num - 1] = num
                swap(nums[i] - 1, i, nums);
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return len + 1;

    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test1() {
        int[] nums = new int[]{-1, 4, 2, 1, 9, 10};
        assertEquals(3, firstMissingPositive(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{-1, 1, 3, 4};
        assertEquals(2, firstMissingPositive(nums));
    }
}
