package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import org.junit.Test;

/*
 * Each employee has one direct manager given in the manager array where manager[i] is the direct
 * manager of the i-th employee, manager[headID] = -1.
 * 
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates
 * 
 * Also it's guaranteed that the subordination relationships have a tree structure.
 */
public class TimeNeededToInformAllEmployees {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        int[] totalTime = new int[n];
        Arrays.fill(totalTime, -1);
        totalTime[headID] = informTime[headID];
        int max = informTime[headID];
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, dfs(i, manager, informTime, totalTime));
        }
        return max;
    }

    // for every node, trace back to its manager
    private int dfs(int curr, int[] manager, int[] informTime, int[] totalTime) {
        if (totalTime[curr] == -1) {
            totalTime[curr] = informTime[curr] + dfs(manager[curr], manager, informTime, totalTime);
        }
        return totalTime[curr];
    }

    @Test
    public void test0() {
        int n = 7, headID = 6;
        int[] manager = {1, 2, 3, 4, 5, 6, -1}, informTime = {0, 6, 5, 4, 3, 2, 1};
        assertEquals(21, numOfMinutes(n, headID, manager, informTime));
    }

    @Test
    public void test1() {
        int n = 1, headID = 0;
        int[] manager = {-1}, informTime = {0};
        assertEquals(0, numOfMinutes(n, headID, manager, informTime));
    }
}
