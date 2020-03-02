package com.leetcode.array;

import java.util.Arrays;
import java.util.PriorityQueue;
import org.junit.Test;

public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) pq.poll();
        }
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.poll();
        }
        return res;
    }


    // Quick sort
    public int[][] kClosest0(int[][] points, int K) {
        int len = points.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int mid = helper(points, left, right);
            if (mid == K) break;
            if (mid < K) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] points, int left, int right) {
        int[] pivot = points[left];
        while (left < right) {
            while (left < right && compare(points[right], pivot) >= 0) {
                right--;
            }
            // points[right] < pivot
            points[left] = points[right];
            while (left < right && compare(points[left], pivot) >= 0) {
                left++;
            }
            // points[left] < pivot
            points[right] = points[left];
        }
        points[left] = pivot;
        return left;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] + p2[1] * p2[1];
    }

    @Test
    public void test0() {
        int[][] points = new int[][] {{1, 3}, {-2, 2}};
        int K = 1;

        int[][] expected = new int[][] {{-2, -2}};
        Arrays.equals(expected, kClosest(points, K));
    }
}
