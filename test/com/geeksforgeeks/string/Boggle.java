package com.geeksforgeeks.string;

import java.util.LinkedList;
import java.util.List;

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
	String[] dictionary;

	private List<String> findWordsUtil(char boggle[][], boolean visited[][], int i, int j, StringBuilder builder) {
		List<String> res = new LinkedList<>();
		// Mark current cell as visited and append current character to str
		visited[i][j] = true;
		builder.append(boggle[i][j]);

		// If str is present in dictionary, then print it
		String tmp = builder.toString();
		if (isWord(tmp)) {
			res.add(tmp);
		}
		// Traverse 8 adjacent cells of boggle[i][j]
		for (int row = i - 1; row <= i + 1 && row < boggle.length; row++) {
			for (int col = j - 1; col <= j + 1 && col < boggle[0].length; col++) {
				if (row >= 0 && col >= 0 && !visited[row][col]) {
					findWordsUtil(boggle, visited, row, col, builder);
				}
			}
		}

		// Erase current character from string and mark visited
		// of current cell as false
		builder.setLength(builder.length() - 1);
		visited[i][j] = false;
		
		return res;
	}

	boolean isWord(String str) {
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equals(str)) {
				return true;
			}
		}
		return false;
	}
}
