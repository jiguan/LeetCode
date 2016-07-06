package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        int i = 0, j = nums.length-1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (nums[mid] == target)
                return true;
            if (nums[mid] < nums[j]) { // minimum in first half
                if (target > nums[mid] && target <= nums[j]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            } else if (nums[mid] > nums[j]) {
                if (target < nums[mid] && target >= nums[i]) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            } else {
                j--;
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
}
