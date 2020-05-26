package com.leetcode.graph.dependency;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import org.junit.Test;

/*
 * Parallel Courses
 * 
 * There are N courses, labelled from 1 to N.
 * 
 * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and
 * course Y: course X has to be studied before course Y.
 * 
 * In one semester you can study any number of courses as long as you have studied all the
 * prerequisites for the course you are studying.
 * 
 * Return the minimum number of semesters needed to study all courses. If there is no way to study
 * all the courses, return -1.
 * 
 * Follow up: If only pick at most k courses in one semester, the shortest time to finish all
 * courses
 * 
 * https://www.sciencedirect.com/science/article/pii/S0022000075800080
 */
public class ParallelCourses {

    // Time & space: O(V + E).
    public int minimumSemesters(int N, int[][] relations) {
        // key: prerequisite, value: course list
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[N + 1];
        for (int[] r : relations) {
            graph.computeIfAbsent(r[0], l -> new ArrayList<>()).add(r[1]); // construct graph.
            ++inDegree[r[1]]; // count prerequisites for r[1].
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; ++i) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        int semester = 0;
        while (!q.isEmpty()) { // BFS traverse all currently 0 in degree vertices.
            for (int size = q.size(); size > 0; --size) { // sz is the search breadth.
                int c = q.poll();
                --N;
                if (!graph.containsKey(c)) {
                    continue; // c's in-degree is currently 0, but it has no prerequisite.
                }
                for (int course : graph.remove(c)) {
                    if (--inDegree[course] == 0) {
                        q.offer(course); // add current 0 in-degree vertex into Queue.
                    }
                }
            }
            ++semester; // need one more semester.
        }
        return N == 0 ? semester : -1;
    }

    @Test
    public void test0() {
        int N = 3;
        int[][] relations = {{1, 3}, {2, 3}};

        // In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is
        // studied.
        assertEquals(2, minimumSemesters(N, relations));
    }

}
