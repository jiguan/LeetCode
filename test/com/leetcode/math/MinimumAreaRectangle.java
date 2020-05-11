package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MinimumAreaRectangle {
	public int minAreaRect(int[][] points) {
		// x - [y1, y2]
		Map<Integer, Set<Integer>> map = new HashMap<>();

		for (int[] p : points) {
			if (!map.containsKey(p[0])) {
				map.put(p[0], new HashSet<>());
			}
			map.get(p[0]).add(p[1]);
		}

		int res = Integer.MAX_VALUE;
		for (int i = 0; i < points.length; ++i) {
			for (int j = 0; j < i; ++j) {
				// find diagonal points
				int[] p1 = points[i];
				int[] p2 = points[j];
				if (p1[0] == p2[0] || p1[1] == p2[1])
					continue;
 
				if (map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
					res = Math.min(res, Math.abs((p1[0] - p2[0]) * (p1[1] - p2[1])));
				}
			}
		}
		return res == Integer.MAX_VALUE ? -1 : res;
	}

	@Test
	public void test0() {
		int[][] points = { { 1, 1 }, { 1, 3 }, { 3, 1 }, { 3, 3 }, { 2, 2 } };
		// {2,2} is ignored
		assertEquals(4, minAreaRect(points));
	}
}
