package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

// Two images A and B are given, represented as binary, square matrices of the same size.  
// The overlap of this translation is the number of positions that have a 1 in both images.
// What is the largest possible overlap?
public class ImageOverlap {
    public int largestOverlap(int[][] A, int[][] B) {

    }

    @Test
    public void test0() {
        int[][] A = new int[][] {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        int[][] B = new int[][] {{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};
        
        assertEquals(3, largestOverlap(A, B));
    }

}
