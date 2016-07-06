package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CompareVersionNumbers {
	public int compareVersion(String version1, String version2) {
		String[] arr1 = version1.split("\\."), arr2 = version2.split("\\.");
		int maxLen = Math.max(arr1.length, arr2.length);
		for(int i = 0;i<maxLen;i++) {
			int num1 = num(arr1, i), num2 = num(arr2, i);
			if(num1> num2) return 1;
			else if(num(arr1, i)< num(arr2, i)) return -1;
		}
		return 0;
	}
	
	private int num(String[] arr, int index) {
		if(index>=arr.length) return 0;
		else return Integer.valueOf(arr[index]);
	}
	
	@Test
	public void test0() {
		String version1 ="1.2", version2 = "13.37";
		assertEquals(-1, compareVersion(version1, version2));
	}
	
	@Test
	public void test1() {
		String version1 ="0000.1", version2 = "0000.1.02";
		assertEquals(-1, compareVersion(version1, version2));
	}
	
}
