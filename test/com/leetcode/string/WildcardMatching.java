package com.leetcode.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class WildcardMatching {
	// greedy algorithm
	public boolean isMatchGreedy(String s, String p) {
		int i = 0, j = 0, matchIndex = 0, starIndex = -1;
		while (i < s.length()) {
			if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
				i++;
				j++;
			} else if (j < p.length() && p.charAt(j) == '*') {
				starIndex = j;
				matchIndex = i;
				j++;
			} else if (starIndex != -1) {
				j = starIndex + 1;
				matchIndex++;
				i = matchIndex;
			} else {
				return false;
			}
		}
		while (j < p.length() && p.charAt(j) == '*')
			j++;

		return j == p.length();
	}

	public boolean isMatchDp(String s, String p) {
		int m = s.length(), n = p.length();
		boolean[] match = new boolean[m + 1];
		match[0] = true;

		for (int j = 0; j < n; j++) {
			if (p.charAt(j) != '*') {
				for(int i=m-1;i>=0;i--) {
					match[i + 1] = match[i] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
				}
				 match[0] = false;
			} else {
				for (int i = 0; i < m; i++) {
					match[i + 1] = match[i] || match[i + 1];
				}
			}
		}
		return match[m];

	}

	private boolean isMatch(String s, String p) {
		return isMatchDp(s, p);
	}

	/*
	 * isMatch("aa","a") -> false isMatch("aa","aa") -> true isMatch("aaa","aa")
	 * -> false isMatch("aa", "*") -> true isMatch("aa", "a*") -> true
	 * isMatch("ab", "?*") -> true isMatch("aab", "c*a*b") -> false
	 */

	@Test
	public void test0() {
		assertFalse(isMatch("aa", "a"));
		assertTrue(isMatch("aa", "aa"));
		assertFalse(isMatch("aaa", "aa"));
	}

	@Test
	public void test1() {
		assertTrue(isMatch("aa", "*"));
		assertTrue(isMatch("aa", "?*"));
	}

	@Test
	public void test2() {
	//	assertTrue(isMatch("abbc", "a*c"));
		assertFalse(isMatch("aab", "c*a*b"));
	}
}
