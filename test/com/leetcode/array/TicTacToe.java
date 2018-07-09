package com.leetcode.array;

public class TicTacToe {
    private int[] columns;
    private int[] rows;
    private int diagonal;
    private int antiDiagonal;
    private int n;

    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        columns = new int[n];
        rows = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
        this.n = n;
    }

    /**
     * Player {player} makes a move at ({row}, {col}).
     * 
     * @param row
     *            The row of the board.
     * @param col
     *            The column of the board.
     * @param player
     *            The player, can be either 1 or 2.
     * @return The current winning condition, can be either: 0: No one wins. 1: Player 1 wins. 2: Player 2 wins.
     */
    public int move(int row, int col, int player) {
        // Take player 1 and 2 as value of 1 and -1;
        int number = player == 1 ? 1 : -1; // place the player, identify which player; player 1 as 1, player 2 as -1;
        rows[row] += number; // save the value to row, col, diag, antiDiag
        columns[col] += number;
        if (row == col) {
            diagonal += number;
        }
        if (row == n - 1 - col) {
            antiDiagonal += number;
        }
        // winning condition
        if (Math.abs(rows[row]) == n || Math.abs(columns[col]) == n || Math.abs(diagonal) == n
                || Math.abs(antiDiagonal) == n) {
            return player;
        }
        return 0;
    }
}