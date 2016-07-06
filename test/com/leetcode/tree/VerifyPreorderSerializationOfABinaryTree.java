package com.leetcode.tree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class VerifyPreorderSerializationOfABinaryTree {
	public boolean isValidSerialization0(String preorder) {
		String[] nodes = preorder.split(",");
		int diff = 1;
		for (String node : nodes) {
			if (--diff < 0)
				return false;
			if (!node.equals("#"))
				diff += 2;
		}
		return diff == 0;
	}

	public boolean isValidSerialization(String preorder) {
		while (true) {
			if (preorder.matches(".*,[0-9]+,#,#.*")) {
				preorder = preorder.replaceAll(",[0-9]+,#,#", ",#");
				continue;
			}
			return preorder.matches("[0-9]+,#,#");
		}
	}

	@Test
	public void test0() {
		String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
		assertTrue(isValidSerialization(preorder));
	}

	@Test
	public void test1() {
		String preorder = "1,#";
		assertFalse(isValidSerialization(preorder));
	}

	@Test
	public void test2() {
		String preorder = "9,#,10,#,#";
		if(preorder.matches(".*,[^#]+,#,#.*")) {
			preorder = preorder.replaceAll(",[^#]+,#,#", ",@");
		}
		assertEquals("9,#,@", preorder);
	}
	
	@Test
	public void test3() {
		String preorder = "9,#,92,#,#";
		assertTrue(isValidSerialization(preorder));
	}
	

	@Test
	public void test4() {
		String preorder = "1,#,#,1";
		assertFalse(isValidSerialization(preorder));
	}
}
