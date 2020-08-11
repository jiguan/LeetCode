package com.leetcode.util;

public class ArrayReader {
    int[] arr;

    public ArrayReader(int[] arr) {
        this.arr = arr;
    }

    public int get(int index) {
        return arr[index];
    }
}
