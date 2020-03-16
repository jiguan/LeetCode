package com.leetcode.array.priorityqueue;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Test;

public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // src - [dst - price]
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.put(flight[0], graph.getOrDefault(flight[0], new HashMap<>()));
            graph.get(flight[0]).put(flight[1], flight[2]);
        }
        // price, k
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
        pq.add(new int[]{0, src, K + 1});
        while (!pq.isEmpty()) {
            int[] flight = pq.remove();
            int price = flight[0];
            int city = flight[1];
            int stops = flight[2];
            if (city == dst) return price;
            if (stops > 0) {
                // dst - price
                Map<Integer, Integer> adj = graph.get(city);
                if (adj != null) {
                    for (int dest : adj.keySet()) {
                        pq.add(new int[]{price + adj.get(dest), dest, stops - 1});
                    }
                }
            }
        }
        return -1;
    }

    @Test
    public void test0() {
        int n = 3;
        int[][] flights = new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
        int src = 0, dst = 2, K = 1;
        assertEquals(200, findCheapestPrice(n, flights, src, dst, K));
    }

    @Test
    public void test1() {
        int n = 5;
        int[][] flights = new int[][]{{4, 1, 1}, {1, 2, 3}, {0, 3, 2}, {0, 4, 10}, {3, 1, 1}, {1, 4, 3}};
        int src = 2, dst = 1, K = 1;
        assertEquals(-1, findCheapestPrice(n, flights, src, dst, K));
    }

}
