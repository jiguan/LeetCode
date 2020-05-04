package com.leetcode.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import org.junit.Test;

public class ValidSquare {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] array = {dist(p1, p2), dist(p1, p3), dist(p1, p4), dist(p2, p3), dist(p2, p4),
                dist(p3, p4)};
        Arrays.sort(array);

        return array[0] > 0 && array[0] == array[1] && array[1] == array[2] && array[2] == array[3]
                && array[4] == array[5];
    }

    private int dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    @Test
    public void test0() {
        int[] p1 = {0, 0}, p2 = {1, 1}, p3 = {1, 0}, p4 = {0, 1};
        assertTrue(validSquare(p1, p2, p3, p4));
    }

    @Test
    public void test1() {
        int[] p1 = {0, 0}, p2 = {0, 0}, p3 = {0, 0}, p4 = {0, 0};
        assertFalse(validSquare(p1, p2, p3, p4));
    }
}
