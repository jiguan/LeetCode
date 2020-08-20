package com.interview.oci;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Given an unsorted array arr[] and two numbers x and y, find the minimum distance between x and y
 * in arr[]. The array might also contain duplicates. You may assume that both x and y are different
 * and present in arr[].
 */
public class FindMinimumDistanceBetweenTwoNumbers {

    public int minDist(int arr[], int x, int y) {
        int res = Integer.MAX_VALUE;
        int prev = -1;
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == x || arr[i] == y) {
                if (prev != -1 && arr[i] != arr[prev]) {
                    res = Math.min(res, i - prev);
                }
                prev = i;
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] arr = {2, 5, 3, 5, 4, 4, 2, 3};
        int x = 3, y = 2;
        assertEquals(1, minDist(arr, x, y));
    }
}
