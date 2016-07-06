package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WordPattern {

	public boolean wordPattern(String pattern, String str) {
		String[] strs = str.split(" ");
		if (strs.length != pattern.length()) return false;
		Map<String, String> map = new HashMap<>();
		for(int i=0;i<strs.length;i++) {
			String word = strs[i];
			String p = String.valueOf(pattern.charAt(i));
			if(map.containsKey(p)) {
				if(map.get(p).equals(word)) continue;
				else return false;
			} else {
				if(map.containsValue(word)) return false;
				else {
					map.put(p, word);
				}
			}
		}
		return true;
	}
	
	@Test
	public void test1() {
		String pattern = "abba";
		String str = "dog cat cat dog";
		assertEquals(true, wordPattern(pattern, str));
	}
	
	@Test
	public void test2() {
		String pattern = "abba";
		String str = "dog dog dog dog";
		assertEquals(false, wordPattern(pattern, str));
	}
	
	@Test
	public void test3() {
		String pattern = "";
		String str = "beef";
		assertEquals(false, wordPattern(pattern, str));
	}
	
	@Test
	public void test() {
		Map map = new HashMap();
		System.out.println(map.put("dog", 1));
		System.out.println(map.put("dog", 2));
		System.out.println(map.put("dog", 3));
	}
}
