package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SortColors {
    public void sortColors(int[] nums) {
        int zeroPointer = 0, twoPointer = nums.length-1;
        int onePointer = 0;
        while(onePointer<=twoPointer) {
            if(nums[onePointer]==0) {
                nums[onePointer] = nums[zeroPointer];
                nums[zeroPointer] = 0;
                onePointer++;
                zeroPointer++;
            } else if(nums[onePointer]==2) {
                nums[onePointer] = nums[twoPointer];
                nums[twoPointer] = 2;
                twoPointer--;
            } else {
                onePointer++;
            }
        }
        
    }

    public void sortColors2pass(int[] nums) {
        if (nums.length == 0)
            return;
        int[] arr = new int[3];
        for (int num : nums) {
            arr[num]++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < arr[0])
                nums[i] = 0;
            else if (i < arr[0] + arr[1])
                nums[i] = 1;
            else
                nums[i] = 2;
        }
    }

    @Test
    public void test0() {
        int[] nums = new int[] {0, 0, 1, 2, 1, 2, 0};
        sortColors(nums);
        assertArrayEquals(new int[] {0, 0, 0, 1, 1, 2, 2}, nums);
    }

    @Test
    public void test1() {
        int[] nums = new int[] {2};
        sortColors(nums);
        assertArrayEquals(new int[] {2}, nums);
    }
    
    @Test
    public void test2() {
        int[] nums = new int[] {1,0};
        sortColors(nums);
        assertArrayEquals(new int[] {0, 1}, nums);
    }
}

