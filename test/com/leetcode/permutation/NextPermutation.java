package com.leetcode.permutation;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class NextPermutation {
    public void nextPermutation0(int[] nums) {
        // find an element that breaks descending order
        int index = nums.length - 2;
        while (index >= 0 && nums[index] >= nums[index + 1]) {
            index--;
        }
        // search from tail to head, find an element larger than nums[index]
        for (int i = nums.length - 1; index >= 0 && i >= 0; i--) {
            if (nums[i] > nums[index]) {
                swap(nums, i, index);
                break;
            }
        }

        // since index + 1 to tail is in descending order, reverse index + 1 to tail
        int start = index + 1, end = nums.length - 1;
        while (start <= end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void nextPermutation(int[] nums) {
        // get index of num smaller than the number after it.
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            --i;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                --j;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test0() {
        int[] nums = new int[]{1, 2, 3};
        nextPermutation(nums);
        assertArrayEquals(new int[]{1, 3, 2}, nums);
    }

    @Test
    public void test1() {
        int[] nums = new int[]{1, 3, 2};
        nextPermutation(nums);
        assertArrayEquals(new int[]{2, 1, 3}, nums);
    }

    @Test
    public void test2() {
        int[] nums = new int[]{3, 2, 1};
        nextPermutation(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

}
