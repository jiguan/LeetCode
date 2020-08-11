package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.leetcode.util.ArrayReader;

/*
 * Search in a Sorted Array of Unknown Size
 * 
 * Given an integer array sorted in ascending order, write a function to search target in nums. If
 * target exists, then return its index, otherwise return -1. However, the array size is unknown to
 * you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k)
 * returns the element of the array at index k (0-indexed).
 * 
 * You may assume all integers in the array are less than 10000, and if you access the array out of
 * bounds, ArrayReader.get will return 2147483647.
 * 
 * 
 * Example 1:
 * 
 * Input: array = [-1,0,3,5,9,12], target = 9 Output: 4 Explanation: 9 exists in nums and its index
 * is 4 Example 2:
 * 
 * Input: array = [-1,0,3,5,9,12], target = 2 Output: -1 Explanation: 2 does not exist in nums so
 * return -1
 * 
 * 
 * Note:
 * 
 * You may assume that all elements in the array are unique. The value of each element in the array
 * will be in the range [-9999, 9999].
 * 
 */
public class SearchInASortedArrayOfUnknownSize {
    public int search(ArrayReader reader, int target) {
        int start = 0;
        int end = 1;
        while (reader.get(end) < target) {
            start = end;
            end = end * 2;
        }
        while (start <= end) {
            int mid = (start + end) / 2;
            if (reader.get(mid) > target) {
                end = mid - 1;
            } else if (reader.get(mid) < target) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Test
    public void test0() {
        ArrayReader reader = new ArrayReader(new int[] {-1, 0, 3, 5, 9, 12});
        int target = 9;
        assertEquals(4, search(reader, target));
    }
}
