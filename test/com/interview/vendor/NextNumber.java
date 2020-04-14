package com.interview.vendor;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NextNumber {

    public int next(int num) {
        char[] nums = String.valueOf(num).toCharArray();
        int n = nums.length;
        // From rightmost, find the first num not descending
        int i = nums.length - 2;
        while (i >= 0) {
            if (nums[i] < nums[i + 1]) break;
            --i;
        }
        // all nums are descending
        if (i == -1) {
            for (int k = 0; k < n / 2; ++k) {
                swap(k, n - 1 - k, nums);
            }
        } else {
            // From rightmost, find the first num larger than current value
            int j = nums.length - 1;
            while (j >= 0) {
                if (nums[j] > nums[i]) break;
                --j;
            }
            swap(i, j, nums);
            for (int k = 1; k < (n - i) / 2; ++k) {
                swap(i + k, n - k, nums);
            }
        }
        return Integer.valueOf(String.valueOf(nums));
    }

    private void swap(int i, int j, char[] nums) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test0() {
        int num = 24876;
        int expected = 26478;
        assertEquals(Integer.valueOf(expected), Integer.valueOf(next(num)));
    }

    @Test
    public void test1() {
        int num = 123456;
        int expected = 123465;
        assertEquals(Integer.valueOf(expected), Integer.valueOf(next(num)));
    }

    @Test
    public void test2() {
        int num = 4321;
        int expected = 1234;
        assertEquals(Integer.valueOf(expected), Integer.valueOf(next(num)));
    }
    
    @Test
    public void test3() {
        int num = 15432;
        int expected = 21435;
        assertEquals(Integer.valueOf(expected), Integer.valueOf(next(num)));
    }
}
