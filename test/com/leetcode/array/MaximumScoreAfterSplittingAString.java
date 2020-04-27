package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Given a string s of zeros and ones, return the maximum score after splitting
 * the string into two non-empty substrings (i.e. left substring and right
 * substring).
 * 
 * The score after splitting a string is the number of zeros in the left
 * substring plus the number of ones in the right substring.
 * 
 * Example 1:
 * 
 * Input: s = "011101" Output: 5 Explanation: All possible ways of splitting s
 * into two non-empty substrings are:
 * 
 * left = "0" and right = "11101", score = 1 + 4 = 5
 * 
 * left = "01" and right = "1101", score = 1 + 3 = 4
 * 
 * left = "011" and right = "101", score = 1 + 2 = 3
 * 
 * left = "0111" and right = "01", score = 1 + 1 = 2
 * 
 * left = "01110" and right = "1", score = 2 + 1 = 3
 *
 */
public class MaximumScoreAfterSplittingAString {
	public int maxScore(String s) {
		int right = 0;
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == '1')
				right++;
		}

		int left = 0;
		int max = 0;
		for (int i = 0; i < s.length() - 1; ++i) {
			if (s.charAt(i) == '0') {
				left++;
			} else {
				right--;
			}
			max = Math.max(max, left + right);
		}

		return max;
	}

	@Test
	public void test0() {
		String s = "011101";
		assertEquals(Integer.valueOf(5), Integer.valueOf(maxScore(s)));
	}

	@Test
	public void test1() {
		String s = "1111";
		assertEquals(Integer.valueOf(3), Integer.valueOf(maxScore(s)));
	}

	@Test
	public void test2() {
		String s = "0000";
		assertEquals(Integer.valueOf(3), Integer.valueOf(maxScore(s)));
	}
}
