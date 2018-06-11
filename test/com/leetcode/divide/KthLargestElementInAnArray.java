package com.leetcode.divide;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        return partition(0, nums.length - 1, nums, k);
    }

    private int partition(int low, int high, int nums[], int k) {
        int i = low, j = high, pivot = low;
        while (i <= j) {
            while (i <= j && nums[i] <= nums[pivot])
                i++;
            while (i <= j && nums[j] > nums[pivot])
                j--;
            if (i > j) break;
            swap(nums, i, j);
        }
        swap(nums, j, pivot);
        pivot = j;
        if (pivot == nums.length - k) return nums[pivot];
        else if (pivot < nums.length - k) return partition(pivot + 1, high, nums, k);
        else
            return partition(low, pivot - 1, nums, k);
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
    }

    @Test
    public void test0() {
        int[] nums = new int[]{6, 5, 4, 3, 2, 1};
        int k = 2;
        assertEquals(5, findKthLargest(nums, k));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, 3};
        int k = 3;
        assertEquals(1, findKthLargest(nums, k));
    }
}
