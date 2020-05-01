package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.Test;

public class ShortestDistanceFromAllBuildings {
    int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int shortestDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] distances = new int[m][n];

        List<Tuple> buildings = new ArrayList<>();

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    buildings.add(new Tuple(i, j, 0));
                }
                // since for '1' and '2' they cannot pass, mark them as negative
                grid[i][j] = -grid[i][j];
            }
        }


        for (int k = 0; k < buildings.size(); ++k) {
            bfs(buildings.get(k), k, distances, grid, m, n);
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                // if grid[i][j] is accessible by all buildings
                if (grid[i][j] == buildings.size()) {
                    ans = Math.min(ans, distances[i][j]);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void bfs(Tuple root, int k, int[][] dist, int[][] grid, int m, int n) {
        Queue<Tuple> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Tuple building = queue.poll();
            dist[building.x][building.y] += building.dist;
            for (int[] dir : dirs) {
                int x = building.x + dir[0];
                int y = building.y + dir[1];

                // grid[x][y] can be visited by the previous building k
                if (y >= 0 && x >= 0 && x < m && y < n && grid[x][y] == k) {
                    grid[x][y] = k + 1;
                    queue.add(new Tuple(x, y, building.dist + 1));
                }
            }
        }
    }

    class Tuple {
        public int x, y, dist;

        public Tuple(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    @Test
    public void test0() {
        int[][] grid = {{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        assertEquals(7, shortestDistance(grid));
    }
}
