package com.leetcode.util;

public class Robot {
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int[][] room;
    private int x, y, pointer = 0;

    public Robot(int[][] room, int[] loc, int[] next) {
        this.room = room;
        this.x = loc[0];
        this.y = loc[1];
        for (int i = 0; next != null && i < dirs.length; ++i) {
            int[] dir = dirs[i];
            if (dir[0] == next[0] && dir[1] == next[1]) {
                pointer = i;
                break;
            }
        }
    }

    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    public boolean move() {
        int next_x = x + dirs[pointer][0];
        int next_y = y + dirs[pointer][1];
        if (next_x >= room.length || next_x < 0 || next_y >= room[0].length || next_y < 0
                || room[next_x][next_x] == 0) {
            return false;
        } else {
            x = next_x;
            y = next_y;
            return true;
        }
    }

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft() {
        pointer = (pointer - 1 + 4) % 4;
    }

    public void turnRight() {
        pointer = (pointer + 1) % 4;
    }

    // Clean the current cell.
    public void clean() {
        if (room[x][y] == 1) {
            room[x][y] = -1;
        }
    }
}
