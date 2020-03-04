package com.leetcode.divide;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        return partition(0, nums.length - 1, nums, k);
    }

    private int partition(int start, int end, int nums[], int k) {
        int i = start, j = end, pivot = start;
        // we need to have equals, since we may not to swap
        while (i <= j) {
            if (nums[i] >= nums[pivot]) {
                ++i;
            } else if (nums[j] < nums[pivot]) {
                --j;
            } else {
                // encounter nums[i] < nums[j] situation, swap and continue;
                swap(nums, i, j);
            }
        }
        // since i must be + 1, j smallest is 0
        // while i could be out of boundary
        swap(nums, j, pivot);
        pivot = j;
        // since pivot is index, need to +1
        if (pivot + 1 > k) {
            return partition(start, pivot - 1, nums, k);
        } else if (pivot + 1 < k) {
            return partition(pivot + 1, end, nums, k);
        } else {
            return nums[pivot];
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        nums[j] ^= nums[i];
        nums[i] ^= nums[j];
        nums[j] ^= nums[i];
    }

    @Test
    public void test0() {
        int[] nums = new int[] {6, 5, 4, 3, 2, 1};
        int k = 2;
        assertEquals(5, findKthLargest(nums, k));
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1, 2, 3};
        int k = 3;
        assertEquals(1, findKthLargest(nums, k));
    }

    @Test
    public void test2() {
        int[] nums = new int[] {3, 1, 2, 3, 4, 5, 6};
        int k = 2;
        assertEquals(5, findKthLargest(nums, k));
    }
}
