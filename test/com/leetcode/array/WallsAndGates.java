package com.leetcode.array;

import java.util.LinkedList;

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
        LinkedList<int[]> list = new LinkedList<int[]>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0)
                    list.add(new int[] {i, j});
            }
        }
        int[][] diff = new int[][] {{-1, 0, 1, 0}, {0, 1, 0, -1}};
        while (!list.isEmpty()) {
            int[] pop = list.remove();
            for (int i = 0; i < diff[0].length; i++) {
                int newR = pop[0] + diff[0][i];
                int newC = pop[1] + diff[1][i];
                if (newR < 0 || newR >= rooms.length || newC < 0 || newC >= rooms[0].length
                        || rooms[newR][newC] != Integer.MAX_VALUE)
                    continue;
                rooms[newR][newC] = rooms[pop[0]][pop[1]] + 1;
                list.add(new int[] {newR, newC});
            }
        }
    }
}
