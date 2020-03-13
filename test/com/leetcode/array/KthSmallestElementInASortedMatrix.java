package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the
 * kth smallest element in the matrix. Note that it is the kth smallest element in the sorted order,
 * not the kth distinct element.
 * 
 * matrix = [ [ 1, 5, 9], [10, 11, 13], [12, 13, 15] ], k = 8, return 13.
 * 
 */

public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int small = matrix[0][0], big = matrix[n - 1][n - 1];

        while (small < big) {
            int midVal = (small + big) / 2;
            int count = 0; // number of elements no bigger than mid

            for (int i = 0; i < n; i++) {
                int[] row = matrix[i];
                int left = 0, right = row.length;
                while (left < right) {
                    int mid = (left + right) / 2;
                    int value = row[mid];
                    if (value > midVal) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                count += left;
            }

            if (count < k) {
                small = midVal + 1;
            } else {
                big = midVal;
            }
        }
        return small;
    }

    @Test
    public void test0() {
        int[][] matrix = new int[][] {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        assertEquals(9, kthSmallest(matrix, 3));
    }

}
