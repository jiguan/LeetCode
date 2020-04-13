package com.leetcode.math;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MedianOfTwoSortedArrays {
    private static final double DELTA = 1e-15;
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if (m > n) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int i = 0, j = 0, i_start = 0, i_end = m, half = (m + n + 1) / 2;
        double maxLeft = 0, minRight = 0;
        while (i_start <= i_end) {
            i = (i_start + i_end) / 2;
            j = half - i;
            if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {
                i_start = i + 1;
            } else if (i > 0 && j < n && nums1[i - 1] > nums2[j]) {
                i_end = i - 1;
            } else {
                if (i == 0) {
                    maxLeft = (double) nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = (double) nums1[i - 1];
                } else {
                    maxLeft = (double) Math.max(nums1[i - 1], nums2[j - 1]);
                }
                break;
            }
        }
        if ((m + n) % 2 == 1) {
            return maxLeft;
        }
        if (i == m) {
            minRight = (double) nums2[j];
        } else if (j == n) {
            minRight = (double) nums1[i];
        } else {
            minRight = (double) Math.min(nums1[i], nums2[j]);
        }

        return (double) (maxLeft + minRight) / 2;
    }

    @Test
    public void testFindMedianSortedArrays() {
        int[] nums1 = new int[] {};
        int[] nums2 = new int[] {2, 3};

        assertEquals(2.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }

    @Test
    public void testFindMedianSortedArrays1() {
        int[] nums1 = new int[] {1, 3};
        int[] nums2 = new int[] {2};

        assertEquals(2, findMedianSortedArrays(nums1, nums2), DELTA);
    }

    @Test
    public void testFindMedianSortedArrays2() {
        int[] nums1 = new int[] {1, 3, 5, 7};
        int[] nums2 = new int[] {2, 4, 6, 8};

        assertEquals(2.5, findMedianSortedArrays(nums1, nums2), DELTA);
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
}
