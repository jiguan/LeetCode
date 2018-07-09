package com.hackerank.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class FindMissingNums {
    public int[] find(int[] nums, int miss) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }

        int index = 0;
        int[] res = new int[miss];
        Set<Integer> exists = new HashSet<>();
        for (int i = 0; i < nums.length + miss; i++) {
            if (i < nums.length && nums[i] != i + 1) {
                res[index++] = i + 1;
                exists.add(nums[i]);
            }
            if (i >= nums.length) {
                if (!exists.contains(i + 1)) {
                    res[index++] = i + 1;
                }
            }
        }
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test0() {
        int[] nums = {8, 9, 10, 1, 3, 5};
        int miss = 4;
        int[] expected = {2, 4, 6, 7};
        assertArrayEquals(expected, find(nums, miss));
    }
}
