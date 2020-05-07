package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

// Two images A and B are given, represented as binary, square matrices of the same size.
// sliding it left, right, up, or down any number of units
// The overlap of this translation is the number of positions that have a 1 in both images.
// What is the largest possible overlap?
public class ImageOverlap {
    public int largestOverlap0(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || A[0].length == 0) return 0;
        List<int[]> arr1 = new ArrayList<>();
        List<int[]> arr2 = new ArrayList<>();

        int n = A.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (A[i][j] == 1) arr1.add(new int[] {i, j});
                if (B[i][j] == 1) arr2.add(new int[] {i, j});
            }
        }

        int res = 0;
        // diff - count
        Map<String, Integer> counts = new HashMap<>();
        for (int[] a : arr1) {
            for (int[] b : arr2) {
                String diff = (a[0] - b[0]) + "-" + (a[1] - b[1]);
                counts.put(diff, counts.getOrDefault(diff, 0) + 1);
                res = Math.max(res, counts.get(diff));
            }
        }
        return res;
    }

    public int largestOverlap(int[][] A, int[][] B) {
        int max = 0;
        for(int i = A.length - 1; i >= 0; i--) {
            for(int j = B.length -1; j >= 0; j--) {
                int sum1 = 0, sum2 = 0;
                for(int k = 0; i + k < A.length; k++) {
                    for(int l = 0; l + j < A.length; l++) {
                        sum1 += A[i+k][l+j] & B[k][l];
                        sum2 += B[i+k][l+j] & A[k][l];
                    }
                }
                max = Math.max(max,Math.max(sum1, sum2));
            }
        }
        return max;
    }
    
    @Test
    public void test0() {
        int[][] A = new int[][] {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        int[][] B = new int[][] {{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};

        assertEquals(3, largestOverlap(A, B));
    }

}
