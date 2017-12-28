package com.leetcode.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] indegree = new int[numCourses];
		for (int[] pair : prerequisites) {
			++indegree[pair[1]];
		}

		Queue<Integer> unlimitedCourse = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (indegree[i] == 0) {
				unlimitedCourse.offer(i);
			}
		}
		while (!unlimitedCourse.isEmpty()) {
			int course = unlimitedCourse.poll();
			--numCourses;
			for (int[] pair : prerequisites) {
				if (pair[0] == course) {
					if (--indegree[pair[1]] == 0) {
						unlimitedCourse.offer(pair[1]);
					}
				}
			}
		}
		return numCourses == 0;
	}

	@Test
	public void test0() {
		int numCourses = 2;
		int[][] prerequisites = new int[][] { { 1, 0 } };
		assertTrue(canFinish(numCourses, prerequisites));
	}

	@Test
	public void test1() {
		int numCourses = 2;
		int[][] prerequisites = new int[][] { { 1, 0 }, { 0, 1 } };
		assertFalse(canFinish(numCourses, prerequisites));
	}

	@Test
	public void test2() {
		int numCourses = 3;
		int[][] prerequisites = new int[][] { { 1, 0 }, { 0, 2 }, { 2, 1 } };
		assertFalse(canFinish(numCourses, prerequisites));
	}

}
