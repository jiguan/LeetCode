package com.leetcode.design;

import static org.junit.Assert.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import com.leetcode.util.Pair;
import com.leetcode.util.Robot;

/*
 * Robot Room Cleaner
 * 
 * Given a robot cleaner in a room modeled as a grid.
 * 
 * Each cell in the grid can be empty or blocked.
 * 
 * The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made
 * is 90 degrees.
 * 
 * When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on
 * the current cell.
 * 
 * Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 * 
 * Notes:
 * 
 * 1. The input is only given to initialize the room and the robot's position internally. You must
 * solve this problem "blindfolded". In other words, you must control the robot using only the
 * mentioned 4 APIs, without knowing the room layout and the initial robot's position.
 * 
 * 2. The robot's initial position will always be in an accessible cell.
 * 
 * 3. The initial direction of the robot will be facing up.
 * 
 * 4. All accessible cells are connected, which means the all cells marked as 1 will be accessible
 * by the robot.
 * 
 * 5. Assume all four edges of the grid are all surrounded by wall.
 */
public class RobotRoomCleaner {
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public void cleanRoom(Robot robot) {
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        backtrack(robot, 0, 0, 0, visited);
    }

    private void backtrack(Robot robot, int row, int col, int dir,
            Set<Pair<Integer, Integer>> visited) {
        visited.add(new Pair<>(row, col));
        robot.clean();
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (int i = 0; i < 4; ++i) {
            int newDir = (dir + i) % 4;
            int newRow = row + directions[newDir][0];
            int newCol = col + directions[newDir][1];

            if (!visited.contains(new Pair<>(newRow, newCol)) && robot.move()) {
                backtrack(robot, newRow, newCol, newDir, visited);
                goBack(robot);
            }
            // turn the robot following chosen direction : clockwise
            robot.turnRight();
        }
    }

    private void goBack(Robot robot) {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }

    private boolean isAllCleaned(int[][] room) {
        for (int i = 0; i < room.length; ++i) {
            for (int j = 0; j < room[0].length; ++j) {
                if (room[i][j] == 1) return false;
            }
        }
        return true;
    }

    @Test
    public void test0() {
        int[][] room = {{1, 1, 1, 1, 1, 0, 1, 1}, {1, 1, 1, 1, 1, 0, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1}, {0, 0, 0, 1, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1, 1, 1}};
        int row = 1, col = 3;
        Robot robot = new Robot(room, new int[] {row, col}, null);
        cleanRoom(robot);
        assertTrue(isAllCleaned(room));
    }
}
