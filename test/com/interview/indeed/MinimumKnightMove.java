package com.interview.indeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class MinimumKnightMove {
    int[] directarget_x = new int[] {1, 1, -1, -1, 2, 2, -2, -2};
    int[] directarget_y = new int[] {2, -2, 2, -2, 1, -1, 1, -1};

    // bfs
    public int shortestPath(Set<Point> obstacle, Point source, Point destination) {
        if (source == null || destination == null)
            return -1;
        if (source.equals(destination))
            return 0;

        Queue<Point> queue = new LinkedList<>();
        Map<Point, Integer> map = new HashMap<>();
        map.put(source, 0); // record steps to reach this node since we are using bfs
        Set<Point> visited = new HashSet<>();
        queue.offer(source);
        visited.add(source);
        while (!queue.isEmpty()) {
            Point curr = queue.poll();
            if (curr.x == destination.x && curr.y == destination.y)
                return map.get(curr);
            for (Point next : getNeighbors(curr)) {
                if (obstacle.contains(next) || visited.contains(next)) {
                    continue;
                }
                queue.offer(next);
                visited.add(next);
                map.put(next, map.get(curr) + 1);
            }
        }

        return -1;
    }

    private List<Point> getNeighbors(Point point) {
        List<Point> result = new ArrayList<>();
        for (int i = 0; i < directarget_x.length; i++) {
            result.add(new Point(point.x + directarget_x[i], point.y + directarget_y[i]));
        }
        return result;
    }

    // bottom up
    int[][] dp = new int[100][100];

    public int getsteps(int x, int y, int target_x, int target_y) {
        // if knight is on the target
        // position return 0.
        if (x == target_x && y == target_y) {
            return dp[0][0];
        } else // if already calculated then return
        // that value. Taking absolute difference.
        if (dp[Math.abs(x - target_x)][Math.abs(y - target_y)] != 0) {
            return dp[Math.abs(x - target_x)][Math.abs(y - target_y)];
        } else {

            // there will be two distinct positions
            // from the knight towards a target.
            // if the target is in same row or column
            // as of knight than there can be four
            // positions towards the target but in that
            // two would be the same and the other two
            // would be the same.
            int x1, y1, x2, y2;

            // (x1, y1) and (x2, y2) are two positions.
            // these can be different according to situation.
            // From position of knight, the chess board can be
            // divided into four blocks i.e.. N-E, E-S, S-W, W-N .
            if (x <= target_x) {
                if (y <= target_y) {
                    x1 = x + 2;
                    y1 = y + 1;
                    x2 = x + 1;
                    y2 = y + 2;
                } else {
                    x1 = x + 2;
                    y1 = y - 1;
                    x2 = x + 1;
                    y2 = y - 2;
                }
            } else if (y <= target_y) {
                x1 = x - 2;
                y1 = y + 1;
                x2 = x - 1;
                y2 = y + 2;
            } else {
                x1 = x - 2;
                y1 = y - 1;
                x2 = x - 1;
                y2 = y - 2;
            }

            // ans will be, 1 + minimum of steps
            // required from (x1, y1) and (x2, y2).
            dp[Math.abs(x - target_x)][Math.abs(y - target_y)] =
                    Math.min(getsteps(x1, y1, target_x, target_y),
                            getsteps(x2, y2, target_x, target_y)) + 1;

            // exchanging the coordinates x with y of both
            // knight and target will result in same ans.
            dp[Math.abs(y - target_y)][Math.abs(x - target_x)] =
                    dp[Math.abs(x - target_x)][Math.abs(y - target_y)];
            return dp[Math.abs(x - target_x)][Math.abs(y - target_y)];
        }
    }



    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj instanceof Point) {
                Point another = (Point) obj;
                return this.x == another.x && this.y == another.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
    }
}
