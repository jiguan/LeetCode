package com.leetcode.priorityqueue;

import static org.junit.Assert.assertEquals;

import java.util.PriorityQueue;

import org.junit.Test;

public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;
        PriorityQueue<Tuple> queue = new PriorityQueue<>();
        for (int j = 0; j < matrix.length; j++) {
            queue.offer(new Tuple(0, j, matrix[0][j]));
        }
        int res = queue.peek().val;
        while (k-- > 0 && !queue.isEmpty()) {
            Tuple t = queue.poll();
            res = t.val;
            if (t.x < matrix.length - 1) {
                queue.offer(new Tuple(t.x + 1, t.y, matrix[t.x + 1][t.y]));
            }
        }
        return res;
    }

    @Test
    public void test0() {
        int[][] matrix = {{1, 5, 9}, {10, 11, 13}, {12, 13, 15}};
        int k = 8;

        assertEquals(13, kthSmallest(matrix, k));
    }

}


class Tuple implements Comparable<Tuple> {
    int x, y, val;

    Tuple(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }

    @Override
    public int compareTo(Tuple that) {
        return this.val - that.val;
    }

    @Override
    public String toString() {
        return String.format("Tuple(%d, %d, %d)", this.x, this.y, this.val);
    }


}
