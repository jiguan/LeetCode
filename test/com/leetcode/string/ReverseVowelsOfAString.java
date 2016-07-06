package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class ReverseVowelsOfAString {
	public String reverseVowels(String s) {
		if (s == null || s.length() < 2)
			return s;
		char[] chars = s.toCharArray();
		Set<Character> vowels = new HashSet<>(Arrays.asList('A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'));

		Stack<Character> stack = new Stack<>();
		for (char c : chars) {
			if (vowels.contains(c)) {
				stack.push(c);
			}
		}
		if (stack.isEmpty())
			return s;
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (vowels.contains(c)) {
				chars[i] = stack.pop();
			}
		}
		return new String(chars);
	}

	@Test
	public void test0() {
		String s = "leetcode";
		assertEquals("leotcede", reverseVowels(s));
	}
}
