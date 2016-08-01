package com.algorithm;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class InsertSort {
    public void sort(int[] nums) {
        // look back, starting from 0 makes no sense
        for(int i=1;i<nums.length;i++) {
            int elem = nums[i];
            int j = i-1;
            while(j>=0 && nums[j] > elem) {
                nums[j+1] = nums[j];
                j--;
            }
            //nums[j] < elem
            nums[j+1] = elem;
        }
    }
    
    @Test
    public void test0() {
        int[] nums = new int[]{6,5,4,3,2,1};
        sort(nums);
        PrettyPrint.print(nums);
    }
}
