package com.geeksforgeeks.test;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;
import com.leetcode.util.TrieNode;

/*
 * Given a dictionary, a method to do lookup in dictionary and a M x N board where every cell has one character. 
 * Find all possible words that can be formed by a sequence of adjacent charactersNote that we can move to any of 8 adjacent characters, 
 * but a word should not have multiple instances of same cell.

	Example:
	
	Input: dictionary[] = {"GEEKS", "FOR", "QUIZ", "GO"};
	       boggle[][]   = {{'G','I','Z'},
	                       {'U','E','K'},
	                       {'Q','S','E'}};
	      isWord(str): returns true if str is present in dictionary
	                   else false.
	
	Output:  Following words of dictionary are present
	         GEEKS
	         QUIZ
 */
public class Boggle {
	// as dictionary
	private TrieNode root = new TrieNode();
	private char[][] boggle;
	private boolean[][] visited;

	public void insert(String word) {
		char[] chars = word.toCharArray();
		TrieNode node = root;

		for (char c : chars) {
			if (node.getNode(c) == null) {
				node.setNode(c, new TrieNode());
			}
			node = node.getNode(c);
		}

		node.isEnd = true;
	}

	public TrieNode match(String word) {
		TrieNode node = root;
		for (char c : word.toCharArray()) {
			node = node.getNode(c);
			if (node == null)
				return null;
		}
		return node;
	}

	private List<String> findWords() {
		List<String> res = new LinkedList<>();
		for (int i = 0; i < boggle.length; i++) {
			for (int j = 0; j < boggle[0].length; j++) {
				findWordsUtil(boggle, visited, i, j, new StringBuilder(), res);
			}
		}
		return res;
	}

	private void findWordsUtil(char boggle[][], boolean visited[][], int i, int j, StringBuilder builder,
			List<String> result) {
		// Mark current cell as visited and append current character to str
		visited[i][j] = true;
		builder.append(boggle[i][j]);

		// If str is present in dictionary, then print it
		String tmp = builder.toString();
		TrieNode node = match(tmp);
		if (node != null) {
			if (node.isEnd) {
				result.add(tmp);
			}

			// Traverse 8 adjacent cells of boggle[i][j]
			for (int row = i - 1; row <= i + 1 && row < boggle.length; row++) {
				for (int col = j - 1; col <= j + 1 && col < boggle[0].length; col++) {
					if (row >= 0 && col >= 0 && !visited[row][col]) {
						findWordsUtil(boggle, visited, row, col, builder, result);
					}
				}
			}

		}
		// Erase current character from string and mark visited
		// of current cell as false
		builder.setLength(builder.length() - 1);
		visited[i][j] = false;
	}

	@Test
	public void test0() {
		insert("geeks");
		insert("for");
		insert("quiz");
		insert("go");

		boggle = new char[][] { { 'g', 'i', 'z' }, { 'u', 'e', 'k' }, { 'q', 's', 'e' } };
		visited = new boolean[boggle.length][boggle[0].length];

		PrettyPrint.print(findWords());

	}
}
