package com.leetcode.graph.prerequisite;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.Test;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // prerequisites - unlocked course
        // since we want to know which course we should finish next
        // key - prerequisite val - unlocked courses
        List<List<Integer>> unlocks = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            unlocks.add(new ArrayList<>());
        }

        // one course could have many prerequisites
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            // finish pair[1] to unlock pair[0]
            unlocks.get(pair[1]).add(pair[0]);
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

            for (int unlock : unlocks.get(course)) {
                if (--indegree[unlock] == 0) {
                    queue.add(unlock);
                }
            }
        }
        return visited == numCourses ? res : new int[0];
    }

    @Test
    public void test0() {
        int numCourses = 2;
        int[][] prerequisites = new int[][] {{1, 0}};
        assertArrayEquals(new int[] {0, 1}, findOrder(numCourses, prerequisites));
    }

    @Test
    public void test1() {
        int numCourses = 4;
        int[][] prerequisites = new int[][] {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] actual = findOrder(numCourses, prerequisites);
        assertArrayEquals(new int[] {0, 1, 2, 3}, actual);
    }
}
