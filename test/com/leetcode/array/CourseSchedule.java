package com.leetcode.array;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[][] preCurMat = new int[numCourses][numCourses];
		int[] hasPreCourse = new int[numCourses];
		for(int[] pair : prerequisites) {
			int cur = pair[0], pre = pair[1];
			if(preCurMat[pre][cur]==0) {
				hasPreCourse[cur]++;
			}
			preCurMat[pre][cur] = 1;			
		}
		
		Queue<Integer> unlimitedCourse = new LinkedList<>();
		for(int i=0;i<numCourses;i++) {
			if(hasPreCourse[i]==0) {
				unlimitedCourse.offer(i);
			}
		}
		int reachableCourse = 0;
		while(!unlimitedCourse.isEmpty()) {
			int course = unlimitedCourse.poll();
			for(int i=0;i<numCourses;i++) {
				if(preCurMat[course][i]!=0) {
					if(--hasPreCourse[i]==0) {
						unlimitedCourse.offer(i);
					}
				}
			}
			reachableCourse++;
		}
		return reachableCourse==numCourses;
	}
	
	@Test
	public void test0() {
		int numCourses = 2;
		int[][] prerequisites = new int[][]{{1,0}};
		assertTrue(canFinish(numCourses, prerequisites));
	}
	
	@Test
	public void test1() {
		int numCourses = 2;
		int[][] prerequisites = new int[][]{{1,0}, {0,1}};
		assertFalse(canFinish(numCourses, prerequisites));
	}
	
	@Test
	public void test2() {
		int numCourses = 3;
		int[][] prerequisites = new int[][]{{1,0}, {0,2}, {2,1}};
		assertFalse(canFinish(numCourses, prerequisites));
	}
	
}
