package practice.first.array;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Stack;

import org.junit.Test;

public class NumberOfIslands {
	public int numIslands(char[][] grid) {
		int count = 0;
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				if(grid[i][j]=='1') {
					count++;
					DFS(grid, i, j);
				}
			}
		}
		return count;
	}

	private void DFS(char[][] grid, int i, int j) {
		if(i>=grid.length || i<0 || j>=grid[0].length || j<0 || grid[i][j]!='1') return;
		grid[i][j] = '0';
		DFS(grid, i+1, j);
		DFS(grid, i, j+1);
		DFS(grid, i-1, j);
		DFS(grid, i, j-1);
	}
	
	
//	private void DFS(char[][] grid, int i, int j) {
//		Stack<Integer> stack = new Stack<>(); 
//		stack.push(j);
//		stack.push(i);
//		while(!stack.isEmpty()) {
//			int a = stack.pop();
//			int b = stack.pop();
//			grid[a][b] = '0';
//			if(a>0 && grid[a-1][b]=='1') {
//				stack.push(b);
//				stack.push(a-1);
//			}
//			if(a<grid.length-1 && grid[a+1][b]=='1') {
//				stack.push(b);
//				stack.push(a+1);
//			}
//			if(b>0 && grid[a][b-1]=='1') {
//				stack.push(b-1);
//				stack.push(a);
//			}
//			if(b<grid[0].length-1 && grid[a][b+1]=='1') {
//				stack.push(b+1);
//				stack.push(a);
//			}
//		}
//	}
	
	private void prettyPrint(char[][] grid) {
		for(char[] g : grid) {
			for(char c : g) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
	
	@Test
	public void test0() {
		String[] grid_str = new String[]{"11000","11000","00100","00011"};
		char[][] grid = new char[grid_str.length][grid_str[0].length()];
		for(int i=0;i<grid_str.length;i++) {
			grid[i] = grid_str[i].toCharArray();
		}
		assertEquals(3, numIslands(grid));
	}
	
	@Test
	public void test1() {
		String[] grid_str = new String[]{"11110","11010","11000","00000"};
		char[][] grid = new char[grid_str.length][grid_str[0].length()];
		for(int i=0;i<grid_str.length;i++) {
			grid[i] = grid_str[i].toCharArray();
		}
		assertEquals(1, numIslands(grid));
	}
	
	@Test
	public void test2() {
		String[] grid_str = new String[]{"11111","11111","11111","11111"};
		char[][] grid = new char[grid_str.length][grid_str[0].length()];
		for(int i=0;i<grid_str.length;i++) {
			grid[i] = grid_str[i].toCharArray();
		}
		assertEquals(1, numIslands(grid));
	}
}
