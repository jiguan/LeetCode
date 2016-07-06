package com.algorithm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinaryIndexTree {
    int[] arr;

    public void build(int[] nums) {
        arr = new int[nums.length + 1];
        arr[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            int removeRightMostOne = i & (i - 1);
            int rightMostOne = i & (~i + 1);
            // arr[i] = nums[removeRightMostOne];
            for(int j=0;j<rightMostOne;j++){ 
                arr[i] += nums[removeRightMostOne + j];
            }
        }

    }

    // get sum frequency till index, starting from 0
    public int getSum(int index) {
        int sum = 0;
        index++;
        while (index > 0) {
            sum += arr[index];
            index -= (index & -index);
        }
        return sum;
    }

    // update index to this value
    public void update(int index, int value) {
        index++;
        int diff = value - arr[index];
        while (index < arr.length) {
            arr[index] += diff;
            index += index & (~index + 1);
        }
    }

    @Test
    public void test0() {
        int[] nums = new int[] {3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};
        BinaryIndexTree bit = new BinaryIndexTree();
        bit.build(nums);
        assertEquals(10, bit.getSum(3));
        assertEquals(19, bit.getSum(7));
        assertEquals(28, bit.getSum(10));
        bit.update(0, 0);
        assertEquals(25, bit.getSum(10));
    }


}
