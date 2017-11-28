package com.leetcode.tree;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        if(numCourses==0||prerequisites.length==0||prerequisites[0].length==0) return new int[0];
        int index = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Set<Integer> accessible = new HashSet<>();
        Set<Integer> inaccessible = new HashSet<>();
        for(int[] pair : prerequisites) {
            int start = pair[1], end = pair[0];
            map.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
            inaccessible.add(end);
            if(!inaccessible.contains(start)) {
                accessible.add(start);
            }
            if(accessible.contains(end)) {
                accessible.remove(end);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(accessible);
        Set<Integer> visited = new HashSet<>();
        while(!queue.isEmpty()) {
            int course = queue.poll();
            if(visited.add(course)) {
                if(map.containsKey(course)) {
                    queue.addAll(map.get(course));
                }
                res[index++] = course;
            }
        }
        return res;
    }
    
    @Test
    public void test0() {
        int numCourses = 2;
        int[][] prerequisites = new int[][]{{1,0}};
        int[] expected = new int[]{0,1};
        assertArrayEquals(expected, findOrder(numCourses, prerequisites));
    }
    
    @Test
    public void test1() {
        int numCourses = 4;
        int[][] prerequisites = new int[][]{{1,0},{2,0},{3,1},{3,2}};
        int[] expected = new int[]{0,1,2,3};
        assertArrayEquals(expected, findOrder(numCourses, prerequisites));
    }
    
    @Test
    public void test2() {
        int numCourses = 1;
        int[][] prerequisites = new int[][]{{}};
        int[] expected = new int[0];
        assertArrayEquals(expected, findOrder(numCourses, prerequisites));
    }
}
