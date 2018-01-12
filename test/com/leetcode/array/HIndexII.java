package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HIndexII {
    // citations[index] >= length(citations) - index.
    public int hIndex(int[] citations) {
        int left = 0, len = citations.length, right = len - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (citations[mid] < len - mid) {
                left = mid + 1;
            } else if (citations[mid] > len - mid) {
                right = mid - 1;
            } else {
                return citations[mid];
            }
        }

        return len - left;
    }

    @Test
    public void test0() {
        int[] citations = new int[]{1};
        assertEquals(1, hIndex(citations));
    }
}
