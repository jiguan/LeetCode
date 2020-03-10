package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RangeSumQuery2DMutable {

    @Test
    public void test0() {
        int[][] matrix = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix mat = new NumMatrix(matrix);
        assertEquals(8, mat.sumRegion(2, 1, 4, 3));
        mat.update(3, 2, 2);
        assertEquals(10, mat.sumRegion(2, 1, 4, 3));
    }


    @Test
    public void test1() {
        int[][] matrix =
                {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}, {17, 18, 19, 20}};
        NumMatrix mat = new NumMatrix(matrix);
        assertEquals(135, mat.sumRegion(2, 1, 4, 3));
        mat.update(3, 2, 2);
        assertEquals(122, mat.sumRegion(2, 1, 4, 3));
    }

}


class NumMatrix {

    // rowSum[i][j] = the sum (matrix[i][0], matrix[i][1], ..., matrix[i][j]).
    int[][] rowSum = new int[0][];
    int[][] matrix;

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        if (matrix != null && matrix.length > 0 && matrix[0].length > 0) {
            rowSum = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[0].length; ++j) {
                    if (j == 0) {
                        rowSum[i][j] = matrix[i][j];
                    } else {
                        rowSum[i][j] = rowSum[i][j - 1] + matrix[i][j];
                    }
                }
            }
        }
    }

    public void update(int row, int col, int val) {
        int diff = val - matrix[row][col];
        matrix[row][col] = val;
        for (int j = col; j < rowSum[0].length; ++j) {
            rowSum[row][j] = rowSum[row][j] + diff;
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i <= row2; ++i) {
            res += (rowSum[i][col2] - (col1 > 0 ? rowSum[i][col1 - 1] : 0));
        }
        return res;
    }
}
