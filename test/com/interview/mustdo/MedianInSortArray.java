package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MedianInSortArray {
    // return the median of nums which is equal or larger than the given value
    // the array is sorted
    public int getMedia(int[] nums, int value) {
        int index = search(nums, value);
        if ((nums.length - index) % 2 == 1) {
            return nums[(nums.length - index) / 2 + index];
        } else {
            int index1 = (nums.length - 1 - index) / 2 + index;
            int index2 = index1 + 1;
            return (nums[index2] - nums[index1]) / 2 + nums[index1];
        }
    }

    // return the index of first element equals or larger then the val
    private int search(int[] nums, int val) {
        int start = 0, end = nums.length;
        while (start < end) {
            int mid = (end - 1 - start) / 2 + start;
            if (nums[mid] > val) {
                end = mid;
            } else if (nums[mid] < val) {
                start = mid + 1;
            } else {
                int res = mid;
                while (res > 0 && nums[res - 1] == nums[res]) {
                    res--;
                }
                return res;
            }
        }
        return end;
    }

    @Test
    public void test0() {
        int[] nums = {1, 1, 2, 3, 4, 5};
        assertEquals(0, search(nums, 1));
    }

    @Test
    public void test1() {
        int[] nums = {1, 1, 2, 3, 4, 5};
        assertEquals(6, search(nums, 6));
    }

    @Test
    public void test2() {
        int[] nums = {1, 1, 2, 3, 4, 5};
        assertEquals(0, search(nums, 0));
    }

    @Test
    public void test3() {
        int[] nums = {1, 1, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1};
        assertEquals(Integer.MAX_VALUE - 2, getMedia(nums, 2));
    }

    @Test
    public void test4() {
        int[] nums = {1, 1, Integer.MAX_VALUE - 10, Integer.MAX_VALUE - 4, Integer.MAX_VALUE - 2,
                Integer.MAX_VALUE - 10};
        assertEquals(Integer.MAX_VALUE - 3, getMedia(nums, 2));
    }
}
