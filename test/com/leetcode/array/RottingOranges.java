package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class RottingOranges {
	public int orangesRotting(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int fresh = 0;
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					fresh++;
				} else if (grid[i][j] == 2) {
					queue.add(new int[] { i, j });
				}
			}
		}
		
		int min = 0;
		if(fresh == 0) return 0;
		// there are rotten oranges
		int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		while (!queue.isEmpty()) {
			int size = queue.size();
			min++;
			for (int s = 0; s < size; ++s) {
				int[] point = queue.poll();
				for (int[] dir : dirs) {
					int i = dir[0] + point[0];
					int j = dir[1] + point[1];

					if (i >= m || i < 0 || j >= n || j < 0)
						continue;
					if (grid[i][j] == 1) {
						grid[i][j] = 2;
						queue.add(new int[] { i, j });
						fresh--;
					}
				}
			}
		}

		if (fresh == 0)
			return min - 1;
		else
			return -1;
	}

	@Test
	public void test0() {
		int[][] grip = new int[][] { { 2, 1 }, { 1, 1 } };
		int expected = 2;
		assertEquals(expected, orangesRotting(grip));
	}
	
	@Test
	public void test1() {
		int[][] grip = new int[][] { { 0 } };
		int expected = 0;
		assertEquals(expected, orangesRotting(grip));
	}
}
