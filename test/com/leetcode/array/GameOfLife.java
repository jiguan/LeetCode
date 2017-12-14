package com.leetcode.array;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class GameOfLife {
    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0) return;

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                int lives = liveNeighbor(board, i, j);
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3;
                } else if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] >>= 1;
            }
        }
    }

    private int liveNeighbor(int[][] board, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(board.length - 1, i + 1); ++x) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(board[0].length - 1, j + 1); ++y) {
                lives += board[x][y] & 1;
            }
        }

        lives -= board[i][j];
        return lives;
    }

    @Test
    public void test1() {
        int[][] board = new int[][]{{1, 1}, {1, 0}};
        gameOfLife(board);
        int[][] result = new int[][]{{1, 1}, {1, 1}};
        assertArrayEquals(result, board);
    }

}
