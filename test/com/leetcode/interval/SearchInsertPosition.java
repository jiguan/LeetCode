package com.leetcode.interval;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        // need to have = so that start could be end, next round it can be length
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return start;
    }

    @Test
    public void test0() {
        int[] nums = {1, 3, 5, 6};
        int target = 2;
        assertEquals(1, searchInsert(nums, target));
    }

    @Test
    public void test1() {
        int[] nums = {1, 3, 5, 6};
        int target = 7;
        assertEquals(4, searchInsert(nums, target));
    }
}
