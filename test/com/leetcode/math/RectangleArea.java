package com.leetcode.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RectangleArea {
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		double width1 = C - A, width2 = G - E, height1 = D - B, height2 = H - F;
		double fullWidth = Double.max(G, C) - Double.min(A, E);
		double wDiff = width1 + width2 - fullWidth;
		if(wDiff<0) wDiff = 0;
		double fullHeight = Double.max(D, H) - Double.min(B, F);
		double hDiff = height1 + height2 - fullHeight;
		if(hDiff<0) hDiff = 0;
		double area1 = width1 * height1, area2 = width2 * height2, share = wDiff * hDiff;
		return (int)(area1 + area2 - share);
	}
	
	@Test
	public void test0() {
		int area = computeArea(-2, -2, 2, 2, -2, -2, 2, 2);
		assertEquals(16, area);
	}
	
	@Test
	public void test1() {
		int area = computeArea(-3, -1, -1, 1, 1, -1, 3, 1);
		assertEquals(8, area);
	}
	
	@Test
	public void test2() {
		int area = computeArea(-1500000001, 0, -1500000000, 1, 1500000000, 0, 1500000001, 1);
		assertEquals(2, area); 
	}
	
	@Test
	public void test3() {
		double max = Double.max(1500000001, -1500000000);
		double min = Double.min(-1500000001, 1500000000);
		double diff = max - min;
		System.out.println(diff);
	}
}
