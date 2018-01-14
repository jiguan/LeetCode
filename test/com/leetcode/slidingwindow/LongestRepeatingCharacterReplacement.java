package com.leetcode.slidingwindow;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Given a string that consists of only uppercase English letters, you can
 * replace any letter in the string with another letter at most k times. Find
 * the length of a longest substring containing all repeating letters you can
 * get after performing the above operations.
 */
public class LongestRepeatingCharacterReplacement {
	public int characterReplacement(String s, int k) {
		int[] count = new int[26];

		int maxCount = 0;
		int start = 0;
		for (int end = 0; end < s.length(); ++end) {
			// current char's count within window
			maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
			if (maxCount + k <= end - start) {
				count[s.charAt(start) - 'A']--;
				start++;
			}
		}
		return s.length() - start;
	}

	@Test
	public void test0() {
		String s = "ABAB";
		int k = 2;
		assertEquals(4, characterReplacement(s, k));
	}
	
	@Test
	public void test1() {
		String s = "AABABBA";
		int k = 1;
		assertEquals(4, characterReplacement(s, k));
	}
}
