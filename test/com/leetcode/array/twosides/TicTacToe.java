package com.leetcode.array.twosides;

public class TicTacToe {
    private int[] columns; 
    private int[] rows;
    private int diagonal = 0;
    private int antiDiagonal = 0;
    private int n;
 
    /** Initialize your data structure here. */
    public TicTacToe(int n) {
        columns = new int[n];
        rows = new int[n];
        this.n = n;
    }
 
    public int move(int row, int col, int player) {
        //Take player 1 and 2 as value of 1 and -1;
        int number = player == 1 ? 1 : -1;  // place the player, identify which player; player 1 as 1, player 2 as -1;
        rows[row] += number;   // save the value to row, col, diag, antiDiag
        columns[col] += number;
        if (row == col) {
            diagonal += number;
        }
        if (row == n - 1 - col) {
            antiDiagonal += number;
        }
        if (Math.abs(rows[row]) == n || Math.abs(columns[col]) == n ||  // winning condition
             Math.abs(diagonal) == n || Math.abs(antiDiagonal) == n)
            return player;
        return 0;
    }
}
