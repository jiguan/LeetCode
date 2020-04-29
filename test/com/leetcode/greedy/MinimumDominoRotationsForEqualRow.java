package com.leetcode.greedy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * In a row of dominoes, A[i] and B[i] represent the top and bottom halves of
 * the i-th domino. (A domino is a tile with two numbers from 1 to 6 - one on
 * each half of the tile.)
 * 
 * We may rotate the i-th domino, so that A[i] and B[i] swap values.
 * 
 * Return the minimum number of rotations so that all the values in A are the
 * same, or all the values in B are the same.
 * 
 * If it cannot be done, return -1.
 *
 */
public class MinimumDominoRotationsForEqualRow {
	public int minDominoRotations(int[] A, int[] B) {
		int[] countA = new int[7], countB = new int[7], same = new int[7];
		int len = A.length;
		for (int i = 0; i < len; ++i) {
			countA[A[i]]++;
			countB[B[i]]++;
			if (A[i] == B[i]) {
				same[A[i]]++;
			}
		}

		for (int i = 1; i < 7; ++i) {
			if (countA[i] + countB[i] - same[i] == len) {
				return len - Math.max(countA[i], countB[i]);
			}
		}

		return -1;
	}

	@Test
	public void test0() {
		int[] A = { 2, 1, 2, 4, 2, 2 }, B = { 5, 2, 6, 2, 3, 2 };
		assertEquals(2, minDominoRotations(A, B));
	}
}
