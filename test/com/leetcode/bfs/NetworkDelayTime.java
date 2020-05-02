package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

// times[i] = (u, v, w), where u is the source node, v is the target node,
// and w is the time it takes for a signal to travel from source to target.
// we send a signal from a certain node K. How long will it take for all
// nodes to receive the signal?
public class NetworkDelayTime {
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

        Map<Integer, Integer> visited = new HashMap<>();
        // It is must to avoid the situation pointing back to K
        visited.put(K, 0);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{K, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (!map.containsKey(curr[0])) continue;
            
            // check the distance from root to current node, if a shorter path is found
            if (visited.containsKey(curr[0]) && visited.get(curr[0]) < curr[1]) {
                continue;
            }

            Map<Integer, Integer> neighbors = map.get(curr[0]);
            for (Map.Entry<Integer, Integer> entry : neighbors.entrySet()) {
                int absoluteDistance = curr[1] + entry.getValue();
                int next = entry.getKey();
                // be careful '=' is must
                if (visited.containsKey(next) && visited.get(next) <= absoluteDistance) {
                    continue;
                } else {
                    visited.put(next, absoluteDistance);
                    queue.offer(new int[]{next, absoluteDistance});
                }
            }
        }

        if (visited.size() != N) return -1;

        int max = -1;
        for (int val : visited.values()) {
            max = Math.max(max, val);
        }

        return max;
    }

    @Test
    public void test0() {
        int[][] times = new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int N = 4, K = 2;
        assertEquals(Integer.valueOf(2), Integer.valueOf(networkDelayTime(times, N, K)));
    }
    
    @Test
    public void test1() {
        int[][] times = new int[][]{{1, 2, 1}, {2, 1, 3}};
        int N = 2, K = 2;
        assertEquals(3, networkDelayTime(times, N, K));
    }
}
