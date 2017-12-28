package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		// prerequisites - unlocked course
		List<List<Integer>> adj = new ArrayList<>();
		int n = numCourses;
		while (n-- > 0) {
			adj.add(new ArrayList<>());
		}

		int[] indegree = new int[numCourses];
		for (int[] pair : prerequisites) {
			// finish pair[1] to unlock pair[0]
			adj.get(pair[1]).add(pair[0]);
			indegree[pair[0]]++;
		}
		Queue<Integer> queue = new LinkedList<>();
		for (int i = 0; i < indegree.length; ++i) {
			if (indegree[i] == 0) {
				queue.offer(i);
			}
		}
		int visited = 0;
		int[] res = new int[numCourses];
		while (!queue.isEmpty()) {
			int course = queue.poll();
			res[visited++] = course;

			for (int i : adj.get(course)) {
				if (--indegree[i] == 0) {
					queue.add(i);
				}
			}
		}
		return visited == numCourses ? res : new int[0];
	}

	@Test
	public void test0() {
		int numCourses = 2;
		int[][] prerequisites = new int[][] { { 1, 0 } };
		assertArrayEquals(new int[] { 0, 1 }, findOrder(numCourses, prerequisites));
	}

	@Test
	public void test1() {
		int numCourses = 4;
		int[][] prerequisites = new int[][] { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		assertArrayEquals(new int[] { 0, 2, 1, 3 }, findOrder(numCourses, prerequisites));
	}
}
