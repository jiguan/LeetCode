package com.leetcode.array.priorityqueue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import org.junit.Test;

public class FindKPairsWithSmallestSums {
    /*
     * Some observations: For every numbers in nums1, its best partner(yields
     * smallest sum) always starts from nums2[0] since arrays are all sorted;
     * And for a specific number in nums1, its next candidate should be
     * nums1[this specific number] + nums2[current_associated_index + 1], unless
     * out of boundary
     */

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] + a[1] - b[0] - b[1]);
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        for (int i = 0; i < nums1.length && i < k; i++) {
            // 0 is the current index in nums2
            queue.offer(new int[] { nums1[i], nums2[0], 0 });
        }
        while (k-- > 0 && !queue.isEmpty()) {
            int[] curr = queue.poll();
            res.add(new int[] { curr[0], curr[1] });
            if (curr[2] < nums2.length - 1) {
                queue.offer(new int[] { curr[0], nums2[curr[2] + 1], curr[2] + 1 });
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[] nums1 = new int[] { 1, 1, 2 }, nums2 = new int[] { 1, 2, 3 };
        int k = 2;
        List<int[]> expected = Arrays.asList(new int[] { 1, 1 }, new int[] { 1, 1 });
        List<int[]> actual = kSmallestPairs(nums1, nums2, k);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertTrue(Arrays.equals(expected.get(i), actual.get(i)));
        }
    }
}
