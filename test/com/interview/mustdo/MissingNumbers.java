package com.interview.mustdo;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MissingNumbers {
    // From 1 to 100, there are 4 nums missing, find them. The array is sorted
    public int[] missingNumber(int[] nums, int n) {
        int[] res = new int[n];
        int index = 0;
        int count = 0;
        for (int i = 1; i <= nums.length + n; i++) {
            if (nums[index] == i) {
                index++;
            } else {
                res[count++] = i;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums = {3, 4, 5, 7, 8, 9};
        int n = 4;
        int[] expected = {1, 2, 6, 10};
        assertArrayEquals(expected, missingNumber(nums, n));
    }

}
