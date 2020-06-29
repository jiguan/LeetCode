package com.leetcode.array;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 
 * Walls and Gates
 * 
 * You are given a m x n 2D grid initialized with these three possible values.
 * 
 * -1 - A wall or an obstacle. 0 - A gate. INF - Infinity means an empty room. We use the value 231
 * - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than
 * 2147483647. Fill each empty room with the distance to its nearest gate. If it is impossible to
 * reach a gate, it should be filled with INF.
 * 
 * Example:
 * 
 * Given the 2D grid:
 * 
 * @formatter:off
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * @formatter:on
 * 
 * After running your function, the 2D grid should be:
 * @formatter:off
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 *   @formatter:on
 */

public class WallsAndGates {
    public void wallsAndGates(int[][] rooms) {
        Queue<int[]> list = new LinkedList<int[]>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    // add gate
                    list.add(new int[] {i, j});
                }
            }
        }
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!list.isEmpty()) {
            int[] curr = list.remove();
            for (int[] dir : dirs) {
                int next_i = curr[0] + dir[0];
                int next_j = curr[1] + dir[1];
                if (next_i >= 0 && next_i < rooms.length && next_j >= 0 && next_j < rooms[0].length
                        && rooms[next_i][next_j] == Integer.MAX_VALUE) {
                    rooms[next_i][next_j] = rooms[curr[0]][curr[1]] + 1;
                    list.add(new int[] {next_i, next_j});
                }
            }
        }
    }
}
