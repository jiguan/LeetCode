package com.leetcode.dp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;

		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*' && dp[0][i - 1]) {
				// if i-1 match and i is *, then i+1 is always matched
				dp[0][i + 1] = true;
			}
		}
		PrettyPrint.print(dp);
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				System.out.println(i + " " + j);
				if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
					boolean tmp = dp[i][j];
					dp[i + 1][j + 1] = dp[i][j];
				} else if (p.charAt(j) == '*') {
					if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
						//dp[i][j+1] multiple s[i], ex. baa. dp[i][j+1] is a and *
						//dp[i+1][j] only one single letter, * is ignored
						//dp[i+1][j-1] handles ab.*ab, abab situation
						dp[i + 1][j + 1] = dp[i][j + 1] || dp[i + 1][j] || dp[i + 1][j - 1];
					} else {
						// j is *, p[j-1] != s[i], ex. a* gets no match, ignore
						boolean tmp = dp[i + 1][j - 1];
						dp[i + 1][j + 1] = dp[i + 1][j - 1];
					}
				}
			}
		}
		return dp[s.length()][p.length()];
	}

	@Test
	public void test0() {
		assertFalse(isMatch("aa", "a"));
		assertTrue(isMatch("aa", "aa"));
		assertFalse(isMatch("aaa", "aa"));
		assertTrue(isMatch("aa", "a*"));
		assertTrue(isMatch("aa", ".*"));
		assertTrue(isMatch("ab", ".*"));
		assertTrue(isMatch("aab", "c*a*b"));
	}

	@Test
	public void test1() {
		assertFalse(isMatch("abcd", "d*"));
	}
	
	@Test
	public void test2() {
		assertFalse(isMatch("baac", "ba*c"));
	}
	
	@Test
	public void test3() {
		assertFalse(isMatch("aaaa", "a*"));
	}

}
