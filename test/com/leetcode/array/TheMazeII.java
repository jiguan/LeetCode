package com.leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * The Maze II
 * 
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by
 * rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball
 * stops, it could choose the next direction.
 * 
 * Given the ball's start position, the destination and the maze, find the shortest distance for the
 * ball to stop at the destination. The distance is defined by the number of empty spaces traveled
 * by the ball from the start position (excluded) to the destination (included). If the ball cannot
 * stop at the destination, return -1.
 * 
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You
 * may assume that the borders of the maze are all walls. The start and destination coordinates are
 * represented by row and column indexes.
 * 
 * Example 1:
 * 
 * Input 1: a maze represented by a 2D array
 * 
 * @formatter:off
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * @formatter:on
 * 
 * Input 2: start coordinate (rowStart, colStart) = (0, 4) Input 3: destination coordinate (rowDest,
 * colDest) = (4, 4)
 * 
 * Output: 12
 * 
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right. The
 * total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 * 
 */
public class TheMazeII {
    public static final int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || maze[0].length == 0) {
            return -1;
        }

        int m = maze.length;
        int n = maze[0].length;
        int[][] dp = new int[m][n];
        Queue<Pair> que = new LinkedList<>();

        que.offer(new Pair(start[0], start[1], 0));
        for(int i = 0; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }


        while(!que.isEmpty()) {
            Pair cur = que.poll();
            for(int[] dir : dirs) {
                int nextX = cur.x;
                int nextY = cur.y;
                int len = cur.len;
                while(nextX < m && nextX >= 0 && nextY < n && nextY >= 0 && maze[nextX][nextY] == 0) {
                    nextX += dir[0];
                    nextY += dir[1];
                    len++;

                }
                nextX -= dir[0];
                nextY -= dir[1];
                len--;

                // avoid going through unneccessary cases.
                if(len > dp[destination[0]][destination[1]]) {
                    continue;
                }

                if(len < dp[nextX][nextY]) {
                    dp[nextX][nextY] = len;
                    que.offer(new Pair(nextX, nextY, len));
                }
            }
        }

        return dp[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : dp[destination[0]][destination[1]];
    }

    class Pair {
        int x;
        int y;
        int len;
        public Pair(int x, int y, int len) {
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }
}
