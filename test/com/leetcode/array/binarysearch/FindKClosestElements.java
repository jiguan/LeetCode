package com.leetcode.array.binarysearch;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class FindKClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = (left + right) / 2;
            if(Math.abs(x - arr[mid]) > Math.abs(arr[mid + k] - x)) {
            // if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; ++i) {
            res.add(arr[i]);
        }
        return res;
    }

    @Test
    public void test0() {
        int[] arr = {1, 1, 2, 2, 2, 2, 2, 3, 3};
        int x = 3, k = 3;
        List<Integer> expected = Arrays.asList(2, 3, 3);
        assertEquals(expected, findClosestElements(arr, k, x));
    }
}
