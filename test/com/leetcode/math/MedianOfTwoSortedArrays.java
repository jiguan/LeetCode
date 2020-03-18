package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
// https://www.youtube.com/watch?v=LPFhl65R7ww&feature=youtu.be

public class MedianOfTwoSortedArrays {
    private static final double DELTA = 1e-15;

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLen = nums1.length + nums2.length;
        if (totalLen % 2 != 0) {
            return kthSmallest(nums1, nums1.length, 0, nums2, nums2.length, 0,
                    totalLen / 2 + 1);
        } else {
            return (kthSmallest(nums1, nums1.length, 0, nums2, nums2.length, 0, totalLen / 2)
                    + kthSmallest(nums1, nums1.length, 0, nums2, nums2.length,
                            0, totalLen / 2 + 1))
                    / 2.0;
        }
    }

    private int kthSmallest(int[] nums1, int len1, int index1, int[] nums2,
            int len2, int index2, int k) {
        if (len1 > len2)
            return kthSmallest(nums2, len2, index2, nums1, len1, index1, k);
        // if nums1 is empty, no need to compare any more since nums2 is sorted
        if (len1 == 0)
            return nums2[index2 + k - 1];
        if (k == 1)
            return Math.min(nums1[index1], nums2[index2]);
        // need to consider num1 length
        int i = Math.min(len1, k / 2), j = k - i;
        // index1, index2 is the start position, here we just want to compare
        // nums1[i] and nums2[j]
        if (nums1[index1 + i - 1] > nums2[index2 + j - 1])
            // nums1[i] is larger, move index2 to j, and we can make sure
            // [index2, j] are smaller than kth element, shrink the k
            return kthSmallest(nums1, len1, index1, nums2, len2 - j, index2 + j,
                    k - j);
        else if (nums1[index1 + i - 1] < nums2[index2 + j - 1])
            // nums1[i] is smaller, move index1 to i
            return kthSmallest(nums1, len1 - i, index1 + i, nums2, len2, index2,
                    k - i);
        else
            // i+j == k
            return nums1[index1 + i - 1];
    }

    @Test
    public void testKthSmallest1() {
        int[] nums1 = new int[]{1, 2, 3, 4, 5};
        int[] nums2 = new int[]{10, 11, 12, 13};

        assertEquals(2,
                kthSmallest(nums1, nums1.length, 0, nums2, nums2.length, 0, 2));
        assertEquals(10,
                kthSmallest(nums1, nums1.length, 0, nums2, nums2.length, 0, 6));
    }

    @Test
    public void testKthSmallest2() {
        int[] nums1 = new int[]{1, 2, 3, 5, 7};
        int[] nums2 = new int[]{3, 4, 6};

        assertEquals(4,
                kthSmallest(nums1, nums1.length, 0, nums2, nums2.length, 0, 5));
    }

    @Test
    public void testFindMedianSortedArrays() {
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{2, 3};

        assertEquals(2.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }

    @Test
    public void testFindMedianSortedArrays1() {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};

        assertEquals(2, findMedianSortedArrays(nums1, nums2), DELTA);
    }
    
    @Test
    public void testFindMedianSortedArrays2() {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2, 4};

        assertEquals(2.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }
    
    @Test
    public void testFindMedianSortedArrays3() {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};

        assertEquals(2.5, findMedianSortedArrays(nums1, nums2), DELTA);
    }
    
    @Test
    public void testFindMedianSortedArrays4() {
        int[] nums1 = new int[]{2};
        int[] nums2 = new int[]{};  

        assertEquals(2, findMedianSortedArrays(nums1, nums2), DELTA);
    }
}
