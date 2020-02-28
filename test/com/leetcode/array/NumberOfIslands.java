package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberOfIslands {
	public int numIslands(char[][] grid) {
		int res = 0;
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length; ++j) {
				res += search(grid, i, j);
			}
		}
		return res;
	}

	private int search(char[][] grid, int i, int j) {
		int res = 0;
		if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
			grid[i][j] = '0';
			res = 1;
			search(grid, i + 1, j);
			search(grid, i, j + 1);
			search(grid, i, j - 1);
			search(grid, i - 1, j);
		}
		return res;
	}

	private void prettyPrint(char[][] grid) {
		for (char[] g : grid) {
			for (char c : g) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

	@Test
	public void test0() {
		String[] grid_str = new String[] { "11000", "11000", "00100", "00011" };
		char[][] grid = new char[grid_str.length][grid_str[0].length()];
		for (int i = 0; i < grid_str.length; i++) {
			grid[i] = grid_str[i].toCharArray();
		}
		assertEquals(3, numIslands(grid));
	}

	@Test
	public void test1() {
		String[] grid_str = new String[] { "11110", "11010", "11000", "00000" };
		char[][] grid = new char[grid_str.length][grid_str[0].length()];
		for (int i = 0; i < grid_str.length; i++) {
			grid[i] = grid_str[i].toCharArray();
		}
		int res = numIslands(grid);
		prettyPrint(grid);
		assertEquals(1, res);
	}

	@Test
	public void test2() {
		String[] grid_str = new String[] { "11111", "11111", "11111", "11111" };
		char[][] grid = new char[grid_str.length][grid_str[0].length()];
		for (int i = 0; i < grid_str.length; i++) {
			grid[i] = grid_str[i].toCharArray();
		}
		assertEquals(1, numIslands(grid));
	}
}
