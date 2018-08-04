package com.leetcode.hard;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;
/**
 * In the post
 * https://leetcode.com/discuss/76965/3-lines-python-with-explanation-proof, we
 * have learned that , to get wiggle sort, you want to put the number in the
 * following way such that
 * 
 * (1) elements smaller than the ‘median’ are put into the last even slots
 * 
 * (2) elements larger than the ‘median’ are put into the first odd slots
 * 
 * (3) the medians are put into the remaining slots.
 * 
Index :       0   1   2   3   4   5
Small half:   M       S       S    
Large half:       L       L       M
 */

public class WiggleSortII {
    public void wiggleSort(int[] nums) {
        if (nums.length < 2) return;
        Arrays.sort(nums);
        int median = nums[(nums.length + 1) / 2];

        int n = nums.length;
        int left = 0, i = 0, right = n - 1;
        while (i <= right) {
            if (nums[newIndex(i, n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(i++, n));
            } else if (nums[newIndex(i, n)] < median) {
                swap(nums, newIndex(i, n), newIndex(right--, n));
            } else {
                i++;
            }
        }
    }

    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private boolean valid(int[] nums) {
        if (nums.length < 2) return true;
        for (int i = 1; i < nums.length; i += 2) {
            boolean res = (nums[i] > nums[i - 1]) && (i + 1 < nums.length ? (nums[i] > nums[i + 1]) : true);
            if (!res) return false;
        }
        return true;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        wiggleSort(nums);
        assertTrue(valid(nums));
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 2, 4};
        wiggleSort(nums);
        assertTrue(valid(nums));
    }

    @Test
    public void test2() {
        int[] nums = new int[]{6, 5, 4, 3};
        wiggleSort(nums);
        assertTrue(valid(nums));
    }

    @Test
    public void test3() {
        int[] nums = new int[]{6};
        wiggleSort(nums);
        assertTrue(valid(nums));
    }

    @Test
    public void test4() {
        int[] nums = new int[]{4, 5, 5, 6};
        wiggleSort(nums);
        assertTrue(valid(nums));
    }
}
