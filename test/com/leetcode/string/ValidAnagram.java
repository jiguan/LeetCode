package com.leetcode.string;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidAnagram {
	public boolean isAnagram(String s, String t) {
		if(s==null&&t==null) return true;
		else if(s==null||t==null) return false;
		else if(s.length()==0&&t.length()==0) return true;
		else if(s.length()!=t.length()) return false;
		
		char[] s_char = s.toCharArray();
		char[] t_char = t.toCharArray();
		int[] s_times = new int[26];
		for(char c : s_char) {
			s_times[c - 97] += 1;
		}
		for(char c : t_char) {
			if(s_times[c - 97]==0) return false;
			else s_times[c - 97] -= 1;
		}
		return true;
	}
	
	@Test
	public void test1() {
		String s = "car", t = "rat";
		assertFalse(isAnagram(s, t));
	}
	
	@Test
	public void test2() {
		String s = "anagram", t = "nagaram";
		assertTrue(isAnagram(s, t));
	}
}
