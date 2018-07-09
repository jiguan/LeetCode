package com.geeksforgeeks.test;

import static org.junit.Assert.assertArrayEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class FindShortestDistanceFromAGuardInABank {
    public int[][] findDistance(char[][] matrix) {
        int len1 = matrix.length, len2 = matrix[0].length;
        int[][] distance = new int[len1][len2];

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0; i < len1; ++i) {
            for (int j = 0; j < len2; ++j) {
                char c = matrix[i][j];
                if (c == 'W') {
                    distance[i][j] = -1;
                } else if (c == 'G') {
                    queue.add(new Node(i, j, 0));
                }
            }
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.x, y = node.y;
            distance[x][y] = node.distance;

            if (inOpen(x + 1, y, matrix)) {
                queue.add(new Node(x + 1, y, node.distance + 1));
            }
            if (inOpen(x - 1, y, matrix)) {
                queue.add(new Node(x - 1, y, node.distance + 1));
            }
            if (inOpen(x, y + 1, matrix)) {
                queue.add(new Node(x, y + 1, node.distance + 1));
            }
            if (inOpen(x, y - 1, matrix)) {
                queue.add(new Node(x, y - 1, node.distance + 1));
            }
        }

        return distance;
    }

    private boolean inOpen(int x, int y, char[][] matrix) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] == 'O') {
            matrix[x][y] = 'X';
            return true;
        }
        return false;
    }

    class Node {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            super();
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    @Test
    public void test0() {
        // @formatter:off
        char[][] matrix = {
                {'O', 'O', 'O', 'O', 'G'},
                {'O', 'W', 'W', 'O', 'O'},
                {'O', 'O', 'O', 'W', 'O'},
                {'G', 'W', 'W', 'W', 'O'},
                {'O', 'O', 'O', 'O', 'G'}
                };
        int[][] expected = {
            {3, 3, 2, 1, 0},
            {2, -1, -1, 2, 1},
            {1, 2, 3, -1, 2},
            {0, -1, -1, -1, 1},
            {1, 2, 2, 1, 0}};
        // @formatter:on
        assertArrayEquals(expected, findDistance(matrix));
    }
}
