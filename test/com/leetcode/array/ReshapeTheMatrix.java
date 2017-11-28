package com.leetcode.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReshapeTheMatrix {
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		if (nums.length * nums[0].length != r * c)
			return nums;

		int[][] arr = new int[r][c];
		int row = 0, col = 0;
		for (int i = 0; i < r; ++i) {
			for (int j = 0; j < c; ++j) {
				arr[i][j] = nums[row][col++];

				if (col == nums[0].length) {
					++row;
					col = 0;
				}
			}
		}

		return arr;
	}
	
    public boolean hasAlternatingBits(int n) {
        int last = n & 1;
        n >>= 1;
        while(n!=0) {
            if(((n & 1) ^ last) == 1) return true;
            last = n & 1;
            n >>=1;
        }
        return false;
    }

	@Test
	public void test0() {
		int[][] nums = new int[][] { { 1, 2 }, { 3, 4 } };
		int[][] arr = matrixReshape(nums, 1, 4);

		assertEquals(1, arr.length);
		assertEquals(4, arr[0].length);
	}
	
	@Test
	public void test1() {
		int[][] nums = new int[][] {{ 1, 2, 3, 4 } };
		int[][] arr = matrixReshape(nums, 2, 2);

		assertEquals(2, arr.length);
		assertEquals(2, arr[0].length);
	}
}
