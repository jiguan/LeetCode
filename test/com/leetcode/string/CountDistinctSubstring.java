package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

//https://en.wikipedia.org/wiki/Suffix_array
public class CountDistinctSubstring {
	public int count(String s) {
		String[] sub = new String[s.length()];
		for(int i=0;i<s.length();i++) {
			sub[i] = s.substring(i);
		}
		//"a","asds","da","sda"
		Arrays.sort(sub);
		
		
		return 9;
	}
	
	
	@Test
	public void test0() {
		String s = "asds";
		assertEquals(9, count(s));
	}
}
