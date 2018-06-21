package com.hackerank.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

/**
 * There is a colony of 8 cells arranged in a straight line where each day every cell competes with its adjacent
 * cells(neighbor). Each day, for each cell, if its neighbors are both active or both inactive, the cell becomes
 * inactive the next day,. otherwise it becomes active the next day. Assumptions: The two cells on the ends have single
 * adjacent cell, so the other adjacent cell can be assumed to be always inactive. Even after updating the cell state.
 * consider its previous state for updating the state of other cells. Update the cell information of all cells
 * simultaneously. Write a function cellCompete which takes takes one 8 element array of integers cells representing the
 * current state of 8 cells and one integer days representing the number of days to simulate. An integer value of 1
 * represents an active cell and value of 0 represents an inactive cell.
 */
public class CellState {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<Integer> cellCompete(int[] states, int days) {
        for (int i = 0; i < days; i++) {
            PrettyPrint.print(states);
            states = compute(states);
        }
        PrettyPrint.print(states);
        List<Integer> res = new LinkedList<>();
        for (int i : states) {
            res.add(i);
        }
        return res;
    }

    private int[] compute(int[] states) {
        int[] arr = new int[states.length];
        for (int i = 0; i < states.length; i++) {
            if (i == 0) {
                arr[i] = states[i + 1] ^ 0;
            } else if (i == states.length - 1) {
                arr[i] = states[i - 1] ^ 0;
            } else {
                arr[i] = states[i - 1] ^ states[i + 1];
            }
        }
        return arr;
    }

    @Test
    public void test0() {
        int[] states = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
        List<Integer> expected = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1);
        assertEquals(expected, cellCompete(states, 7));
    }
}
