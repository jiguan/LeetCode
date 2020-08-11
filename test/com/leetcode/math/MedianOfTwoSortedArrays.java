package com.leetcode.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MedianOfTwoSortedArrays {

    // leftPart = { nums1[0..p1-1] + nums2[0..p2-1] }
    // rightPart = { nums1[p1..m] + nums[p2..n] }
    // len(leftPart) == len(rightPart) or len(leftPart) == len(rightPart) + 1
    // max(leftPart) <= min(rightPart)

    // O(log(min(m, n)))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        // since we control p1 and calculate p2, we want to make sure p2 is valid
        // make sure n is bigger so that we could adjust p1 freely
        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int p1 = 0, p2 = 0;
        int i_start = 0, i_end = m;

        // make sure leftPart has more elements, so that maxLeft can be used
        // p1 + p2 >= m - p1 + m - p2
        // 2 * (p1 + p2) >= m + n
        // half = p1 + p2 >= (m + n) / 2
        // ceiling the result
        int half = (m + n + 1) / 2;

        double maxLeft = 0, minRight = 0;
        while (i_start <= i_end) {
            p1 = (i_start + i_end) / 2;
            // len(0..p1-1) + len(0..p2-1) = half, len(p1..m) + len(p2..n) = half
            // p1 + p2 = half
            p2 = half - p1;
            // since nums1, nums2 are sorted
            // 0 ... p1-1 | p1 ... m
            // 0 ... p2-1 | p2 ... n

            // if maxLeft > minRight
            if (p2 > 0 && p1 < m && nums2[p2 - 1] > nums1[p1]) {
                // we need to set p2 lower, however p2 is decided by p1
                i_start = p1 + 1;
            } else if (p1 > 0 && p2 < n && nums1[p1 - 1] > nums2[p2]) {
                // we need to make p1 lower
                i_end = p1 - 1;
            } else {
                // maxLeft <= minRight
                if (p1 == 0) {
                    maxLeft = (double) nums2[p2 - 1];
                } else if (p2 == 0) {
                    maxLeft = (double) nums1[p1 - 1];
                } else {
                    maxLeft = (double) Math.max(nums1[p1 - 1], nums2[p2 - 1]);
                }
                break;
            }
        }
        if ((m + n) % 2 == 1) {
            return maxLeft;
        }
        if (p1 == m) {
            minRight = (double) nums2[p2];
        } else if (p2 == n) {
            minRight = (double) nums1[p1];
        } else {
            minRight = (double) Math.min(nums1[p1], nums2[p2]);
        }

        return (double) (maxLeft + minRight) / 2;
    }

    private static final double DELTA = 1e-15;

    @Test
    public void testFindMedianSortedArrays() {
        int[] nums1 = new int[] {};
        int[] nums2 = new int[] {2, 3};
        assertEquals(2.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }

    @Test
    public void testFindMedianSortedArrays1() {
        int[] nums1 = new int[] {1, 3, 5};
        int[] nums2 = new int[] {2};
        assertEquals(2.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }

    @Test
    public void testFindMedianSortedArrays2() {
        int[] nums1 = new int[] {1, 3, 5, 7};
        int[] nums2 = new int[] {2, 4, 6, 8};
        assertEquals(4.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }

    @Test
    public void testFindMedianSortedArrays3() {
        int[] nums1 = new int[] {1, 2};
        int[] nums2 = new int[] {3, 4};
        assertEquals(2.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }

    @Test
    public void testFindMedianSortedArrays4() {
        int[] nums1 = new int[] {2};
        int[] nums2 = new int[] {};
        assertEquals(2, findMedianSortedArrays(nums1, nums2), DELTA);
    }

    @Test
    public void testFindMedianSortedArrays5() {
        int[] nums1 = new int[] {1, 2, 5, 6, 7};
        int[] nums2 = new int[] {4};
        assertEquals(4.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }
}
