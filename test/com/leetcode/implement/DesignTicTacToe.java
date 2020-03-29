package com.leetcode.implement;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DesignTicTacToe {

    @Test
    public void test0() {
        TicTacToe tic = new TicTacToe(3);
        assertEquals(0, tic.move(1, 1, 2));
        assertEquals(0, tic.move(0, 2, 2));
        assertEquals(2, tic.move(2, 0, 2));
    }
}


class TicTacToe {
    private int[] rows, cols;
    private int diag, antiDiag;
    private int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        this.n = n;
        rows = new int[n];
        cols = new int[n];
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * 
     * @param row The row of the board.
     * @param col The column of the board.
     * @param player The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2:
     *         Player 2 wins.
     */
    public int move(int row, int col, int player) {
        int val = player == 1 ? 1 : -1;
        rows[row] += val;
        cols[col] += val;

        if (row == col) {
            diag += val;
        }
        if (row + col == n - 1) {
            antiDiag += val;
        }

        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diag) == n
                || Math.abs(antiDiag) == n) {
            return player;
        }
        return 0;
    }
}

