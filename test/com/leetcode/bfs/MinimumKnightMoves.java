package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import org.junit.Test;

public class MinimumKnightMoves {

    // There is a solution using a math pattern, 0 ms
    // There is another solution using dp
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        Set<String> visited = new HashSet<>();
        visited.add("0,0");
        int[][] dirs = new int[][] {{-2, -1}, {-2, 1}, {-1, 2}, {-1, -2}, {1, -2}, {1, 2}, {2, -1},
                {2, 1}};
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int[] point = queue.poll();
                if (point[0] == x && point[1] == y) {
                    return res;
                }
                for (int[] dir : dirs) {
                    int a = dir[0] + point[0], b = dir[1] + point[1];
                    // if both < 0, we could find a mirror one
                    // if only one < 0, we cannot
                    // however if(a < 0 && b < 0) continue; => (a >= 0 || b >= 0)
                    // more cases are run
                    // so >= -1 is for (1,1) -> (2,-1) -> (0,0)
                    if (a >= -1 && b >= -1 && visited.add(a + "," + b)) {
                        queue.add(new int[] {a, b});
                    }
                }
            }
            res++;
        }
        return -1;
    }


    @Test
    public void test0() {
        int x = 5, y = 5;
        assertEquals(4, minKnightMoves(x, y));
    }

    @Test
    public void test1() {
        int x = 1, y = 1;
        assertEquals(2, minKnightMoves(x, y));
    }
}
