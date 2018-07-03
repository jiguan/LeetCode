package com.algorithm;

import org.junit.Test;

public class QuickSort {

    public void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int p = partition(nums, low, high);
            quickSort(nums, low, p - 1);
            quickSort(nums, p + 1, high);
        }
    }

    private int partition(int[] nums, int low, int high) {
        int index = low, pivot = nums[high];
        for (int i = low; i < high; i++) {
            if (nums[i] < pivot) {
                // make sure nums[i] is always smaller than pivot
                swap(nums, index, i);
                index++;
            }
        }
        swap(nums, index, high);
        return index;
    }

    private void swap(int[] nums, int j, int k) {
        int tmp = nums[j];
        nums[j] = nums[k];
        nums[k] = tmp;
    }

    private void print(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    @Test
    public void test0() {
        int[] nums = new int[]{8, 4, 2, 0, 0, 6, 7};
        quickSort(nums, 0, nums.length - 1);
        print(nums);
    }
}
