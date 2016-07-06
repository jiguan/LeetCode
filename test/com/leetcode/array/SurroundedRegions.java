package com.leetcode.array;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class SurroundedRegions {
	public void solve(char[][] board) {
		if(board==null||board.length==0||board[0].length==0) return;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if(i==0||i==board.length-1||j==0||j==board[0].length-1) {
					if(board[i][j] == 'O')
					dfs(i, 0, board);
				}
			}
		}
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j]== '*') {
					board[i][j] = 'O';
				} else {
					board[i][j] = 'X';
				}
			}
		}
	}

	private void dfs(int i, int j, char[][] board) {
		if (i < 0 || i == board.length || j < 0 || j == board[0].length)
			return;
		if (board[i][j] != 'O')
			return;
		board[i][j]= '*';
		dfs(i + 1, j, board);
		dfs(i - 1, j, board);
		dfs(i, j + 1, board);
		dfs(i, j - 1, board);
	}

	@Test
	public void test0() {
		//@formatter:off
		char[][] board = new char[][]
			{{'X', 'X', 'X', 'X'},
			{'X', 'O', 'O', 'X'},
			{'X', 'X', 'O', 'X'},
			{'X', 'O', 'X', 'X'}};
		 //@formatter:on
		solve(board);
		PrettyPrint.print(board);
	}
}
