package com.leetcode.graph;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.junit.Test;

public class NetworkDelayTime {
    // times[i] = (u, v, w), where u is the source node, v is the target node,
    // and w is the time it takes for a signal to travel from source to target.
    // we send a signal from a certain node K. How long will it take for all
    // nodes to receive the signal?
    public int networkDelayTime(int[][] times, int N, int K) {
        if (times == null || times.length == 0) return -1;

        // source, neighbor - distance
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int[] time : times) {
            if (!map.containsKey(time[0])) {
                map.put(time[0], new HashMap<>());
            }
            Map<Integer, Integer> neighborMap = map.get(time[0]);
            if (!neighborMap.containsKey(time[1]) || neighborMap.get(time[1]) > time[2]) {
                neighborMap.put(time[1], time[2]);
            }
        }

        // Use PriorityQueue to get the node with shortest absolute distance
        // and calculate the absolute distance of its neighbor nodes.
        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(K, 0);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{K, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int neighbor = node[0], distance = node[1];

            if (!map.containsKey(neighbor)) continue;
            if (visited.containsKey(neighbor) && visited.get(neighbor) < distance) {
                continue;
            }

            Map<Integer, Integer> neighborMap = map.get(neighbor);
            for (Map.Entry<Integer, Integer> entry : neighborMap.entrySet()) {
                int absoluteDistance = distance + entry.getValue();
                int nextNode = entry.getKey();

                if (visited.containsKey(nextNode) && visited.get(nextNode) <= absoluteDistance) continue;
                visited.put(nextNode, absoluteDistance);
                queue.offer(new int[]{nextNode, absoluteDistance});
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
}
