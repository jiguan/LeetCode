package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            while (left < nums.length - 1 && nums[left] == nums[left + 1])
                left++;
            while (right > 0 && nums[right] == nums[right - 1])
                right--;

            int mid = (left + right) / 2;
            if (nums[left] == target || nums[right] == target || nums[mid] == target)
                return true;
            if (nums[mid] < nums[right]) { // turn over at first half
                if (target > nums[mid] && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else { // turn over at the second half
                if (target > nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }

    @Test
    public void test0() {
        int[] nums = new int[] {1, 9, 1, 1, 1, 1};
        assertTrue(search(nums, 9));
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1, 1, 1, 1, 9, 1};
        assertTrue(search(nums, 9));
    }

    @Test
    public void test2() {
        int[] nums = new int[] {6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 9, 10, 10, 10, -10, -10, -10, -10, -10, -9, -9, -9, -9, -8, -8, -8, -7, -7, -7, -6, -6, -5, -5, -5, -5, -5, -4,
                -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -3, -3, -3, -3, -3, -2, -2, -2, -1, -1, -1, -1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6,
                6};
        assertTrue(search(nums, 8));
    }
}
