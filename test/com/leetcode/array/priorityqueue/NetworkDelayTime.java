package com.leetcode.array.priorityqueue;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import org.junit.Test;

public class NetworkDelayTime {

    // using PriorityQueue is slower than BFS approach
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times == null || times.length == 0) return -1;

        // source, neighbor - distance
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            if (!map.containsKey(time[0])) {
                map.put(time[0], new HashMap<>());
            }
            Map<Integer, Integer> distMap = map.get(time[0]);
            if (!distMap.containsKey(time[1]) || distMap.get(time[1]) > time[2]) {
                distMap.put(time[1], time[2]);
            }
        }

        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            // get shorter distance
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        pq.offer(new int[] {K, 0});

        int dist = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (!visited.add(curr[0])) continue;
            dist = curr[1];

            if (!map.containsKey(curr[0])) continue;
            Map<Integer, Integer> neighbors = map.get(curr[0]);
            for (Map.Entry<Integer, Integer> entry : neighbors.entrySet()) {
                int next = entry.getKey();
                if (!visited.contains(next)) {
                    pq.offer(new int[] {next, curr[1] + entry.getValue()});
                }
            }
        }

        if (visited.size() != N)
            return -1;
        else
            return dist;
    }

    // using PriorityQueue is not good as BFS approach
    public int networkDelayTimePQ(int[][] times, int N, int K) {
        List<List<int[]>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] time : times) {
            graph.get(time[0]).add(new int[] {time[1], time[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            // get shorter distance
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        pq.offer(new int[] {K, 0});
        Set<Integer> visited = new HashSet<>();
        int dist = 0;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (!visited.add(curr[0])) continue;
            dist = curr[1];
            for (int[] neighbor : graph.get(curr[0])) {
                if (!visited.contains(neighbor[0]))
                    pq.offer(new int[] {neighbor[0], neighbor[1] + curr[1]});
            }
        }
        return visited.size() == N ? dist : -1;
    }

    @Test
    public void test0() {
        int[][] times = new int[][] {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4, K = 2;
        assertEquals(Integer.valueOf(2), Integer.valueOf(networkDelayTime(times, N, K)));
    }
}
