package com.careercup.booking;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/*
 * Given two integer arrays, find the smallest common integer.
 */
public class SmallestCommonInteger {
	public Integer findSmallest(int[] arr1, int[] arr2) {
		Set<Integer> set = new HashSet<>(arr1.length);
		for(int i : arr1) {
			set.add(i);
		}
		Integer res = null;
		for(int i : arr2) {
			if(set.contains(i)&&(res==null || i < res)) {
				res = i;
			}
		}
		return res;
	}
	
	@Test
	public void tes0() {
		int[] arr1 = new int[]{1,3,4,6,7,8}, arr2 = new int[]{2,4,7};
		Integer expect = new Integer(4); 
		assertEquals(expect, findSmallest(arr1, arr2));
	}
	
	@Test
	public void tes1() {
		int[] arr1 = new int[]{1,3,5,7,8}, arr2 = new int[]{2,4,6};
		assertEquals(null, findSmallest(arr1, arr2));
	}
}
