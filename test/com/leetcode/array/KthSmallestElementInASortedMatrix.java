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
        int left = matrix[0][0], right = matrix[n - 1][n - 1];

        while (left < right) {
            int mid = (left + right) / 2;
            int count = 0; // number of elements no bigger than mid

            for (int i = 0; i < n; i++) {
                int[] row = matrix[i];
                int t_left = 0, t_right = row.length;
                while (t_left < t_right) {
                    int t_mid = (t_left + t_right) / 2;
                    int value = row[t_mid];
                    if(value > mid) {
                        t_right = t_mid;
                    } else {
                        t_left = t_mid + 1;
                    }
                }
                count += t_left;
            }

            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    @Test
    public void test0() {
        int[][] matrix = new int[][] {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        assertEquals(9, kthSmallest(matrix, 3));
    }
    
}
