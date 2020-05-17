package com.leetcode.array.binarysearch;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

// You have one chocolate bar that consists of some chunks. Each chunk has its own sweetness given
// by the array sweetness.
// You want to share the chocolate with your K friends so you start cutting the chocolate bar into
// K+1 pieces using K cuts, each piece consists of some consecutive chunks.
// Being generous, you will eat the piece with the minimum total sweetness and give the other pieces
// to your friends.
// Return the total sweetness you will get
public class DivideChocolate {
    public int maximizeSweetness(int[] sweetness, int K) {
        int left = 0, right = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (valid(sweetness, mid, K)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean valid(int[] nums, long target, int K) {
        int count = 1;
        long total = 0;
        for (int num : nums) {
            total += num;
            if (total > target) {
                total = 0;
                count++;
                if (count > K + 1) return false;
            }
        }
        return true;
    }

    @Test
    public void test0() {
        int[] sweetness = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 5;
        // [1,2,3], [4,5], [6], [7], [8], [9]
        // minimum total: 6
        assertEquals(6, maximizeSweetness(sweetness, k));
    }
}
