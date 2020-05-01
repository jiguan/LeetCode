package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import java.util.Random;
import org.junit.Test;

public class RandomPickWithWeight {

    @Test
    public void test0() {
        int[] w = {1, 10, 89};
        Solution solution = new Solution(w);
        assertEquals(1, solution.search(w, 2));
        assertEquals(1, solution.search(solution.sum, 11));
        assertEquals(2, solution.search(solution.sum, 12));
        assertEquals(2, solution.search(solution.sum, 100));
    }

}


class Solution {
    int[] sum;
    java.util.Random random = new Random();

    public Solution(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; ++i) {
            sum[i] = sum[i - 1] + w[i];
        }

    }

    public int pickIndex() {
        int rand = random.nextInt(sum[sum.length - 1]);
        return search(sum, rand + 1);
    }

    int search(int[] arr, int val) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            int tmp = arr[mid];
            if (tmp < val) {
                left = mid + 1;
            } else if (tmp == val) {
                return mid;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
