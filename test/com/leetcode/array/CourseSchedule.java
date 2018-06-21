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
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            int target = pair[0];
            ++indegree[target];
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
                if (pair[1] == course) {
                    if (--indegree[pair[0]] == 0) {
                        unlimitedCourse.offer(pair[0]);
                    }
                }
            }
        }
        return numCourses == 0;
    }

    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>(numCourses);
        for (int[] prerequisity : prerequisites) {
            int target = prerequisity[0], pre = prerequisity[1];
            if (!map.containsKey(target)) {
                map.put(target, new HashSet<>());
            }
            map.get(target).add(pre);
        }

        Queue<Integer> accessible = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                accessible.add(i);
            }
        }

        int accessCourse = 0;
        while (!accessible.isEmpty()) {
            int course = accessible.poll();
            accessCourse++;

            for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
                if (entry.getValue().contains(course)) {
                    entry.getValue().remove(course);
                    if (entry.getValue().isEmpty()) {
                        accessible.add(entry.getKey());
                    }
                }
            }
        }
        return accessCourse == numCourses;
    }

    @Test
    public void test0() {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}};
        assertTrue(canFinish(numCourses, prerequisites));
    }

    @Test
    public void test1() {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1, 0}, {0, 1}};
        assertFalse(canFinish(numCourses, prerequisites));
    }

    @Test
    public void test2() {
        int numCourses = 3;
        int[][] prerequisites = new int[][]{{1, 0}, {0, 2}, {2, 1}};
        assertFalse(canFinish(numCourses, prerequisites));
    }

}
