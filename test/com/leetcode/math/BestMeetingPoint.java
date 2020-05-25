package com.leetcode.math;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/*
 * Best Meeting Point
 * 
 * A group of two or more people wants to meet and minimize the total travel distance. You are given
 * a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is
 * calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 */

public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        List<Integer> list_i = new ArrayList<Integer>();
        List<Integer> list_j = new ArrayList<Integer>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    list_i.add(i);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    list_j.add(j);
                }
            }
        }
        return minSum(list_i) + minSum(list_j);
    }

    // get median to minimize the sum
    public int minSum(List<Integer> list) {
        int head = 0, tail = list.size() - 1;
        int sum = 0;
        while (head < tail) {
            sum += list.get(tail) - list.get(head);
            head++;
            tail--;
        }
        return sum;
    }

    @Test
    public void test0() {
        int[][] grid = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        assertEquals(6, minTotalDistance(grid));
    }
}
