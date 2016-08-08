package com.leetcode.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import com.leetcode.util.PrettyPrint;

public class SpiralMatrix2 {
    public List<Integer> spiralOrder(int[][] matrix) {
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        List<Integer> res = new LinkedList<>();
        int count = matrix.length * matrix[0].length;
        int[] point = new int[] {0, -1};
        int m = matrix[0].length, n = matrix.length - 1;
        int direct = 0;
        while (count > 0) {
            for (int i = 0; i < m; i++) {
                count--;
                point[0] += directions[direct][0];
                point[1] += directions[direct][1];
                res.add(matrix[point[0]][point[1]]);
            }
            int tmp = --m;
            m = n;
            n = tmp;
            direct = (direct + 1) % 4;
        }
        return res;
    }

    @Test
    public void test0() {
        int[][] matrix = new int[][] {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        PrettyPrint.print(spiralOrder(matrix));
    }

    @Test
    public void test1() {
        int[][] matrix = new int[][] {{1, 2}, {6, 3}, {5, 4}};
        PrettyPrint.print(spiralOrder(matrix));
    }

    @Test
    public void test2() {
        int[][] matrix = new int[][] {{1}, {2}, {3}};;

        List<Integer> actual = spiralOrder(matrix);
        List<Integer> expected = Arrays.asList(1, 2, 3);

        assertThat(actual, is(expected));
    }

}
