package practice.first.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

// http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
/*
 * Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. 
 * In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively. 
 * Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. 
 * You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
 */
public class KnapsackProblem {

	public int pack(int[] val, int[] wt, int W) {
		assert (val.length == wt.length);
		int len = val.length;
		int[][] max = new int[len+1][W + 1];

		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= W; j++) {
				int weight = wt[i-1];
				if (weight <= j) {
					max[i][j] = Math.max(max[i-1][j], max[i-1][j - weight] + val[i-1]);
				} else {
					max[i][j] = max[i][j-1];
				}
			}
		}

		prettyPrint(max);
		
		return max[len][W];
	}

	private void prettyPrint(int[][] mat) {
		for(int i = 0; i<mat.length;i++) {
			for(int j=0;j<mat[0].length;j++) {
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void test0() {
		int val[] = { 60, 100, 120 };
		int wt[] = { 10, 20, 30 };
		int W = 50;
		assertEquals(220, pack(val, wt, W));
	}
	
	
}
