package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class RemoveInvalidParentheses {
	public List<String> removeInvalidParentheses(String s) {
		List<String> res = new ArrayList<>();
		removeHelper(s, res, 0, 0, '(', ')');
		return res;
	}

	private void removeHelper(String s, List<String> res, int iStart, int jStart, char open, char close) {
		int stack = 0;
		for (int i = iStart; i < s.length(); ++i) {
			if (s.charAt(i) == open)
				stack++;
			else if (s.charAt(i) == close)
				stack--;
			// there is invalid parenthesis
			if (stack < 0) {
				// traverse all possibilities
				for (int j = jStart; j <= i; j++) {
					// skip duplicates
					if (s.charAt(j) == close && (j == jStart || s.charAt(j - 1) != close)) {
						removeHelper(s.substring(0, j) + s.substring(j + 1), res, i, j, open, close);
					}
				}
				return;
			}
		}
		// all are valid, check opposite direction or reverse it back to original
		String reversed = new StringBuilder(s).reverse().toString();
		if (open == '(') {
			removeHelper(reversed, res, 0, 0, close, open);
		} else {
			res.add(reversed);
		}
	}

	@Test
	public void test0() {
		String s = "(a)())()";
		Set<String> expected = new HashSet<>(Arrays.asList("(a)()()", "(a())()"));
		assertEquals(expected, new HashSet<>(removeInvalidParentheses(s)));
	}
	
	@Test
	public void test1() {
		String s = "(()(()";
		Set<String> expected = new HashSet<>(Arrays.asList("()()", "(())"));
		assertEquals(expected, new HashSet<>(removeInvalidParentheses(s)));
	}
}
