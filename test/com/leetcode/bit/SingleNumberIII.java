package com.leetcode.bit;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class SingleNumberIII {
	public int[] singleNumber (int[] nums) {
		int diff = 0;
		for(int num : nums) {
			diff ^= num;
		}
		diff &= -diff; //get last non-zero bit
		// if it is 1, then two distinct integers must be different at this position since XOR operation
		
		int[] res = new int[2];
		for(int num : nums) {
			if((num & diff) == diff) {
				res[0] ^= num;
			} else {
				res[1] ^= num;
			}
		}
		return res;
	}
	
	
	public int[] singleNumber0(int[] nums) {
		Set<Integer> once = new HashSet<>(nums.length);
		for (int num : nums) {
			if (!once.add(num)) {
				once.remove(num);
			}
		}
		int[] res = new int[once.size()];
		int i = 0;
		for(int n : once) {
			res[i++] = n;
		}
		return res;
	}
	
	@Test
	public void test0() {
		int[] nums = new int[]{-1,0};
		assertArrayEquals(nums, singleNumber(nums));
	}
	
	@Test
	public void test1() {
		int a = Integer.parseInt("1010100", 2);
		System.out.println(a & -a);
	}
}
