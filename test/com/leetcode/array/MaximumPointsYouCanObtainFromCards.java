package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * There are several cards arranged in a row, and each card has an associated
 * number of points The points are given in the integer array cardPoints.
 * 
 * In one step, you can take one card from the beginning or from the end of the
 * row. You have to take exactly k cards.
 * 
 * Your score is the sum of the points of the cards you have taken.
 * 
 * Given the integer array cardPoints and the integer k, return the maximum
 * score you can obtain.
 * 
 * Example 1:
 * 
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * 
 * Output: 12
 * 
 * Explanation: After the first step, your score will always be 1. However,
 * choosing the rightmost card first will maximize your total score. The optimal
 * strategy is to take the three cards on the right, giving a final score of 1 +
 * 6 + 5 = 12.
 *
 */
public class MaximumPointsYouCanObtainFromCards {
	public int maxScore(int[] cardPoints, int k) {
		int len = cardPoints.length;
		int sum = 0;
		for (int i = 0; i < k; ++i) {
			sum += cardPoints[i];
		}
		if (k != len) {
			int newSum = sum;
			for (int i = 0; i < k; ++i) {
				// one by one, remove the last added left
				newSum -= cardPoints[k - 1 - i];
				// instead add right
				newSum += cardPoints[len - 1 - i];
				sum = Math.max(sum, newSum);
			}

		}
		return sum;
	}

	@Test
	public void test0() {
		int[] cardPoints = { 1, 2, 3, 4, 5, 6, 1 };
		int k = 3;
		assertEquals(Integer.valueOf(12), Integer.valueOf(maxScore(cardPoints, k)));
	}

	@Test
	public void test1() {
		int[] cardPoints = { 100, 40, 17, 9, 73, 75 };
		int k = 3;
		assertEquals(Integer.valueOf(248), Integer.valueOf(maxScore(cardPoints, k)));
	}
	
	@Test
	public void test2() {
		int[] cardPoints = { 10, 8, 6, 7, 9, 11 };
		int k = 4;
		assertEquals(Integer.valueOf(38), Integer.valueOf(maxScore(cardPoints, k)));
	}
}
