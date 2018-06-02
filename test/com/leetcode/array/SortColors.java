package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SortColors {
    public void sortColors(int[] nums) {
        int zero = 0, one = 0, two = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                nums[two++] = 2;
                nums[one++] = 1;
                nums[zero++] = 0;
            } else if (nums[i] == 1) {
                nums[two++] = 2;
                nums[one++] = 1;
            } else {
                nums[two++] = 2;
            }
        }
    }

    public void sortColors2pass(int[] nums) {
        if (nums.length == 0) return;
        int[] arr = new int[3];
        for (int num : nums) {
            arr[num]++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i < arr[0]) {
                nums[i] = 0;
            } else if (i < arr[0] + arr[1]) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    @Test
    public void test0() {
        int[] nums = new int[]{0, 0, 1, 2, 1, 2, 0};
        sortColors(nums);
        assertArrayEquals(new int[]{0, 0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    public void test1() {
        int[] nums = new int[]{2};
        sortColors(nums);
        assertArrayEquals(new int[]{2}, nums);
    }

    @Test
    public void test2() {
        int[] nums = new int[]{1, 0};
        sortColors(nums);
        assertArrayEquals(new int[]{0, 1}, nums);
    }
}
