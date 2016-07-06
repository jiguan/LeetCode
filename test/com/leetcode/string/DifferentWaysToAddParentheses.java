package com.leetcode.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DifferentWaysToAddParentheses {
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> result = new ArrayList<>();
		if((input.contains("+")||input.contains("-")||input.contains("*"))==false) {
			result.add(Integer.parseInt(input));
			return result;
		}
		for(int i=0;i<input.length();i++) {
			char op = input.charAt(i);
			if(op=='+'||op=='-'||op=='*') {
				List<Integer> left = diffWaysToCompute(input.substring(0, i));
				List<Integer> right = diffWaysToCompute(input.substring(i+1));
				for(int l : left) {
					for(int r : right) {
						switch (op) {
						case '*': result.add(l*r);
						case '-': result.add(l-r);
						case '+': result.add(l+r);
 						}
					}
				}
			}
		}
		return result;
	}
	
	public List<Integer> diffWaysToCompute0(String input) {
		int length = input.length();
		List<Integer> nums = new ArrayList<>();
		List<Character> ops = new ArrayList<>();
		for(int i=0, j = 0;i<length;i++) {
			char chr = input.charAt(i);
			if(chr=='+'||chr=='-'||chr=='*') {
				ops.add(chr);
				String num = input.substring(j,i);
				nums.add(Integer.parseInt(num));
				j=i+1;
			}
			if(i==length-1) {
				String num = input.substring(j,length);
				nums.add(Integer.parseInt(num));
			}
		}
		return search(nums, 0, nums.size(), ops);
	}
	
	private List<Integer> search(List<Integer> nums, int start, int end, List<Character> ops) {
		List<Integer> result = new ArrayList<>();
		if(end==start+1) {
			result.add(nums.get(start));
			return result;
		}
		for (int i = start+1; i < end; i++) {
			List<Integer> r1 = search(nums, start, i, ops);
			List<Integer> r2 = search(nums, i, end, ops);
			char op = ops.get(i-1);
			result.addAll(cal(r1, r2, op));
		}
		return result;
	}
	
	
	private List<Integer> cal(List<Integer> r1, List<Integer> r2, char op) {
		List<Integer> result = new ArrayList<>(r1.size() + r2.size());
		switch (op) {
		case '+':
			for (int i : r1) {
				for (int j : r2) {
					result.add(i + j);
				}
			}
			break;
		case '-':
			for (int i : r1) {
				for (int j : r2) {
					result.add(i - j);
				}
			}
			break;
		case '*':
			for (int i : r1) {
				for (int j : r2) {
					result.add(i * j);
				}
			}
			break;
		}
		return result;
	}

	@Test
	public void test0() {
		String input = "2+3";
		List<Integer> result = new ArrayList<>();
		result.add(5);
		assertEquals(result, diffWaysToCompute(input));
	}
	
	@Test
	public void test1() {
		String input = "2-1-1";
		List<Integer> result= diffWaysToCompute(input);
		List<Integer> expected = new ArrayList<>();
		expected.add(2);
		expected.add(0);
		assertTrue(result.containsAll(result) && result.containsAll(expected));
	}
	@Test
	public void test2(){
		String input = "2*3-4*5";
		List<Integer> result = diffWaysToCompute(input);
		List<Integer> expected = new ArrayList<>();
		expected.add(-34);
		expected.add(-14);
		expected.add(-10);
		expected.add(-10);
		expected.add(10);
		assertTrue(result.containsAll(result) && result.containsAll(expected));
	}
	
	@Test
	public void test3(){
		String input = "11";
		List<Integer> result = diffWaysToCompute(input);
		List<Integer> expected = new ArrayList<>();
		expected.add(11);
		assertTrue(result.containsAll(result) && result.containsAll(expected));
	}
	
	@Test
	public void test4(){
		String input = "0";
		List<Integer> result = diffWaysToCompute(input);
		List<Integer> expected = new ArrayList<>();
		expected.add(0);
		assertTrue(result.containsAll(result) && result.containsAll(expected));
	}
	
}
