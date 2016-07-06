package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class LargestNumber {
	public String largestNumber(int[] nums) {
		int len = nums.length;
		if (len == 0)
			return "0";
		if (len == 1)
			return String.valueOf(nums[0]);
		String[] arr = new String[len];
		for (int i = 0; i < len; i++) {
			arr[i] = String.valueOf(nums[i]);
		}
		
		Comparator<String> StringComparator = new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				String str1 = s1 + s2, str2 = s2+s1;
				return str2.compareTo(str1);
			}
		};
		
		Arrays.sort(arr, StringComparator);
		if(arr[0].equals("0")) return "0";
		StringBuffer buffer = new StringBuffer();
		for(String s : arr) {
			buffer.append(s);
		}
		return buffer.toString();
	}
	

 
	private void prettyPrint(List<String> list) {
		for(String s : list) {
			System.out.print(s+' ');
		}
		System.out.println();
	}
	@Test
	public void test0() {
		int[] nums = new int[]{11,22,33,44,55,66};
		assertEquals("665544332211", largestNumber(nums));
	}
	
	@Test
	public void test1() {
		int[] nums = new int[]{3, 30, 34, 5, 9};
		assertEquals("9534330", largestNumber(nums));
	}
	
	@Test
	public void test2() {
		int[] nums = new int[]{0,0,0};
		assertEquals("0", largestNumber(nums));
	}
	
	@Test
	public void test3() {
		int[] nums = new int[]{824,938,1399,5607,6973,5703,9609,4398,8247};
		assertEquals("9609938824824769735703560743981399", largestNumber(nums));
	}
}
