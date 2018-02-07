package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class WiggleSortII {
    public void wiggleSort0(int[] nums) {
        if (nums.length < 2) return;
        Arrays.sort(nums);
        int median = nums[(nums.length - 1) / 2 + 1] ;

        int i = 0, end = nums.length - 1;
        int n = nums.length;
        int lw = 1, mw = 1, rw = (1 + 2 * (n - 1)) % (n | 1);
        while (i <= end) {
            if (nums[mw] > median) {
                swap(nums, lw, mw);
                mw = (mw + 2) % (n | 1);
                lw = (lw + 2) % (n | 1);
                ++i;
            } else if (nums[mw] < median) {
                swap(nums, mw, rw);
                rw = (rw - 2 + (n | 1)) % (n | 1);
                --end;
            } else {
                mw = (mw + 2) % (n | 1);
                i++;
            }
        }
    }
    
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int median = nums[(nums.length - 1) / 2 + 1] ;
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
