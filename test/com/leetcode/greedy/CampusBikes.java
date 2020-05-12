package com.leetcode.greedy;

import static org.junit.Assert.assertArrayEquals;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import org.junit.Test;

/*
 * On a campus represented as a 2D grid, there are N workers and M bikes, with N <= M. Each worker
 * and bike is a 2D coordinate on this grid.
 * 
 * Our goal is to assign a bike to each worker. Among the available bikes and workers, we choose the
 * (worker, bike) pair with the shortest Manhattan distance between each other, and assign the bike
 * to that worker. (If there are multiple (worker, bike) pairs with the same shortest Manhattan
 * distance, we choose the pair with the smallest worker index; if there are multiple ways to do
 * that, we choose the pair with the smallest bike index). We repeat this process until there are no
 * available workers.
 * 
 * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y
 * - p2.y|.
 * 
 * Return a vector ans of length N, where ans[i] is the index (0-indexed) of the bike that the i-th
 * worker is assigned to.
 */
public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
            int distance = Integer.compare(a[0], b[0]);
            int worker = Integer.compare(a[1], b[1]);
            int bike = Integer.compare(a[2], b[2]);
            if (distance == 0) {
                if (worker == 0) {
                    return bike;
                } else {
                    return worker;
                }
            } else {
                return distance;
            }
        });

        boolean[] bikeAssigned = new boolean[bikes.length];
        // initializing, each worker is added once only
        for (int i = 0; i < workers.length; i++) {
            // only add the closest bike to each worker
            // a bike may be used twice, but deal with it later
            insertClosestBike(bikeAssigned, workers, bikes, i, pq);
        }

        int[] ans = new int[workers.length];
        while (!pq.isEmpty()) {
            int[] tmp = pq.poll();
            int worker = tmp[1], bike = tmp[2];
            if (!bikeAssigned[bike]) {
                ans[worker] = bike;
                bikeAssigned[bike] = true;
            } else {
                // if that bike has been assigned, add the next closest bike
                insertClosestBike(bikeAssigned, workers, bikes, worker, pq);
            }
        }
        return ans;
    }

    public void insertClosestBike(boolean[] bikeAssigned, int[][] workers, int[][] bikes, int i,
            PriorityQueue<int[]> pq) {
        int index = -1;
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < bikes.length; j++) {
            if (!bikeAssigned[j]) {
                int dist = Math.abs(workers[i][0] - bikes[j][0])
                        + Math.abs(workers[i][1] - bikes[j][1]);
                if (dist < min) {
                    index = j;
                    min = dist;
                }
            }
        }
        pq.offer(new int[] {min, i, index});
    }

    // same shortest Manhattan distance, we choose the pair with the smallest worker index
    // Return an array ans where ans[i] is the index (0-indexed) of the bike that the
    // i-th worker is assigned to.
    public int[] assignBikes1(int[][] workers, int[][] bikes) {
        // int[i] = {dist, worker-i, bike-i}
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
            int distance = Integer.compare(a[0], b[0]);
            int worker = Integer.compare(a[1], b[1]);
            int bike = Integer.compare(a[2], b[2]);
            if (distance == 0) {
                if (worker == 0) {
                    return bike;
                } else {
                    return worker;
                }
            } else {
                return distance;
            }
        });

        for (int i = 0; i < workers.length; ++i) {
            for (int j = 0; j < bikes.length; ++j) {
                int dist = Math.abs(workers[i][0] - bikes[j][0])
                        + Math.abs(workers[i][1] - bikes[j][1]);
                // add all worker-bike combination, sorting is ineffective
                pq.add(new int[] {dist, i, j});
            }
        }

        int[] ans = new int[workers.length];
        Arrays.fill(ans, -1);
        Set<Integer> bikeAssigned = new HashSet<>();

        while (bikeAssigned.size() < workers.length) {
            int[] item = pq.poll();
            int worker = item[1], bike = item[2];
            if (ans[worker] == -1 && !bikeAssigned.contains(bike)) {
                ans[worker] = bike;
                bikeAssigned.add(bike);
            }
        }
        return ans;
    }

    @Test
    public void test0() {
        int[][] workers = {{0, 0}, {2, 1}}, bikes = {{1, 2}, {3, 3}};
        assertArrayEquals(new int[] {1, 0}, assignBikes(workers, bikes));
    }
}
