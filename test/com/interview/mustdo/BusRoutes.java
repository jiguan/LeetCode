package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class BusRoutes {
	public int numBusesToDestination(int[][] routes, int S, int T) {
		if (routes == null || routes.length == 0 || routes[0].length == 0)
			return -1;
		if (S == T)
			return 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(S);
		// stop - bus
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < routes.length; ++i) {
			for (int stop : routes[i]) {
				if (!map.containsKey(stop)) {
					map.put(stop, new LinkedList<>());
				}
				map.get(stop).add(i);
			}
		}
		if (!map.containsKey(T))
			return -1;
		// buses
		Set<Integer> visited = new HashSet<>();
		int res = 0;
		while (!queue.isEmpty()) {
			res++;
			for (int size = queue.size(); size > 0; size--) {
				int stop = queue.poll();
				List<Integer> buses = map.get(stop);
				for (int bus : buses) {
					if (visited.add(bus) == false)
						continue;
					for (int next : routes[bus]) {
						if (next == T)
							return res;
						queue.add(next);
					}
				}
			}
		}
		return -1;
	}

	@Test
	public void test0() {
		int[][] routes = { { 1, 2, 7 }, { 3, 6, 7 } };
		int S = 1, T = 6;
		assertEquals(2, numBusesToDestination(routes, S, T));
	}
}
