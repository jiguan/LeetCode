package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BulbSwitcher {
	public int bulbSwitch(int n) {
		boolean[] bulbs = new boolean[n];
		for(int i = 1;i<=n;i++) {
			for(int j=0;j<n;j=j+i) {
				bulbs[j] = !bulbs[j];
			}
			System.out.print(String.format("Rount %d: ", i+1));
			print(bulbs);
		}
		int result = 0;
		for(boolean bulb : bulbs) {
			if(bulb) {
				result++;
			}
		}
		return result;
	}
	
	private void print(boolean[] arr) {
		for(boolean light : arr) {
			if(light) {
				System.out.print("O ");
			} else {
				System.out.print("X ");
			}
		}
		System.out.println();
	}
	
	@Test
	public void test0() {
		int n = 9;
		assertEquals(3, bulbSwitch(n));
	}
	
	@Test
	public void test1() {
		 bulbSwitch(6);
	}
	
}
