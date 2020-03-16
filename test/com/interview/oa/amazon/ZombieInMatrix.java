package com.interview.oa.amazon;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.Test;

/*
 * Given a 2D grid, each cell is either a zombie 1 or a human 0. Zombies can turn adjacent
 * (up/down/left/right) human beings into zombies every hour. Find out how many hours does it take
 * to infect all humans?
 */
/* @formatter:off
 * Input:
 * [[0, 1, 1, 0, 1],
 *  [0, 1, 0, 1, 0],
 *  [0, 0, 0, 0, 1],
 *  [0, 1, 0, 0, 0]]
 * 
 * Output: 2
 * @formatter:on
 */
public class ZombieInMatrix {
    public int minHours(int rows, int columns, List<List<Integer>> grid) {
        int alive = rows * columns;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.size(); ++i) {
            for (int j = 0; j < grid.get(0).size(); ++j) {
                if (grid.get(i).get(j).equals(1)) {
                    queue.add(new int[] {i, j});
                    alive--;
                }
            }
        }
        int res = 0;
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (alive > 0 && alive != rows * columns) {
            int size = queue.size();
            while (size-- > 0) {
                int[] zombie = queue.poll();
                for (int[] dir : dirs) {
                    int i = zombie[0] + dir[0];
                    int j = zombie[1] + dir[1];

                    if (i >= grid.size() || i < 0 || j >= grid.get(0).size() || j < 0) continue;
                    if (grid.get(i).get(j).equals(0)) {
                        queue.add(new int[] {i, j});
                        grid.get(i).set(j, 1);
                        alive--;
                    }
                }
            }
            res++;
        }
        return res;
    }

    @Test
    public void test0() {
        List<List<Integer>> grid =
                Arrays.asList(Arrays.asList(0, 1, 1, 0, 1), Arrays.asList(0, 1, 0, 1, 0),
                        Arrays.asList(0, 0, 0, 0, 1), Arrays.asList(0, 1, 0, 0, 0));
        assertEquals(2, minHours(4, 5, grid));
    }

    @Test
    public void test1() {
        List<List<Integer>> grid = Arrays.asList(Arrays.asList(0));
        assertEquals(0, minHours(1, 1, grid));
    }
    
    @Test
    public void test2() {
        List<List<Integer>> grid = Arrays.asList(Arrays.asList(1));
        assertEquals(0, minHours(1, 1, grid));
    }
}
