package com.leetcode.array.permutation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] arr = String.valueOf(n).toCharArray();

        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            --i;
        }

        if (i == -1) return -1;

        int j = arr.length - 1;
        while (arr[j] <= arr[i]) {
            --j;
        }
        swap(arr, i, j);

        reverse(arr, i + 1, arr.length - 1);

        String res = new String(arr);
        long val = Long.valueOf(res);
        return val > Integer.MAX_VALUE ? -1 : (int) val;
    }

    private void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start++, end--);
        }
    }

    @Test
    public void test1() {
        int n = 12;
        assertTrue(21 == nextGreaterElement(n));
    }

    @Test
    public void test0() {
        int n = 534976;
        assertTrue(536479 == nextGreaterElement(n));
    }

    @Test
    public void test2() {
        int n = 1999999999;
        assertTrue(-1 == nextGreaterElement(n));
    }

}
