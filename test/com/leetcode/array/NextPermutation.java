package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        // find two elements which first is smaller than the second
        int first = nums.length - 2;
        while (first >= 0 && nums[first] >= nums[first + 1]) {
            first--;
        }
        // search from tail to head, find first element larger than first
        for (int i = nums.length - 1; first>=0 && i >= 0; i--) {
            if (nums[i] > nums[first]) {
                swap(i, first, nums);
                break;
            }
        }
        // reverse first + 1 to tail
        int start = first + 1, end = nums.length - 1;
        while (start <= end) {
            swap(start, end, nums);
            start++;
            end--;
        }
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test0() {
        int[] nums = new int[] {1, 2, 3};
        nextPermutation(nums);
        assertArrayEquals(new int[] {1, 3, 2}, nums);
    }

    @Test
    public void test1() {
        int[] nums = new int[] {1, 3, 2};
        nextPermutation(nums);
        assertArrayEquals(new int[] {2, 1, 3}, nums);
    }
    
    @Test
    public void test2() {
        int[] nums = new int[] {3, 2, 1};
        nextPermutation(nums);
        assertArrayEquals(new int[] {1, 2, 3}, nums);
    }

}
