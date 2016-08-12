package com.leetcode.palindrome;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

/*
 * Rather than use isPalindrome to check it every time, using an array to record
 * dp[i][j]== true means substring(i, j+1) is a valid palindrome
 */

public class PalindromePartitioning {
	public List<List<String>> partition(String s) {
		List<List<String>> res = new LinkedList<>();
		boolean[][] matrix = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < s.length(); j++) {
				//i == j, i == j+1, i == j + 2
				if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || matrix[i + 1][j - 1])) {
					matrix[i][j] = true;
				}
			}
		}
		dfs(s, 0, matrix, new LinkedList<>(), res);
		return res;
	}

	private void dfs(String s, int start, boolean[][] matrix, List<String> current, List<List<String>> res) {
		if (start == s.length()) {
			res.add(new LinkedList<String>(current));
			return;
		}

		for (int i = start; i < s.length(); i++) {
			if (matrix[start][i]) {
				current.add(s.substring(start, i + 1));
				dfs(s, i + 1, matrix, current, res);
				current.remove(current.size() - 1);
			}
		}
	}

	@Test
	public void test0() {
		String s = "aab";
		List<List<String>> res = partition(s);
		for (List<String> list : res) {
			PrettyPrint.print(list);
		}
	}
}
