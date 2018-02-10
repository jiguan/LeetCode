package com.leetcode.array;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.Arrays;

public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0) return board;
        switch (board[x][y]) {
        case 'M':
            board[x][y] = 'X';
        case 'E':
            detectSurrounding(board, x, y);
        }
        return board;
    }

    private void detectSurrounding(char[][] board, int x, int y) {
        // cell needs to be unrevealed.
        if (x >= board.length || x < 0 || y >= board[0].length || y < 0 || board[x][y] != 'E') return;

        int mine = detechMines(board, x, y);
        if (mine == 0) {
            board[x][y] = 'B';
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) continue;
                    detectSurrounding(board, x + i, y + j);
                }
            }

        } else {
            board[x][y] = (char) ('0' + mine);
        }
    }

    private int detechMines(char[][] board, int x, int y) {
        int mine = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i, newY = y + j;
                if (newX >= board.length || newX < 0 || newY >= board[0].length || newY < 0) continue;
                if (board[newX][newY] == 'X' || board[newX][newY] == 'M') mine++;
            }
        }
        return mine;
    }

    @Test
    public void test0() {
        char[][] board = new char[][] { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' },
                { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' } };
        int[] click = new int[] { 3, 0 };
        updateBoard(board, click);
        char[][] expected = new char[][] { { 'B', '1', 'E', '1', 'B' }, { 'B', '1', 'M', '1', 'B' },
                { 'B', '1', '1', '1', 'B' }, { 'B', 'B', 'B', 'B', 'B' } };
        char[][] actual = updateBoard(board, click);
        assertTrue(Arrays.equals(expected, actual));
    }
}
