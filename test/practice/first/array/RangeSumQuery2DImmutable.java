package practice.first.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RangeSumQuery2DImmutable {
	int[][] sum;
	
	public RangeSumQuery2DImmutable() {
	}

	public void setMatrix(int[][] matrix) {
		int m = matrix.length;
		if(m==0) return;
		int n = matrix[0].length;
		if(n==0) return;
		this.sum = new int[m+1][n+1];
		
		for(int i=1;i<m+1;i++) {
			for(int j=1;j<n+1;j++) {
				sum[i][j] = sum[i-1][j] + sum[i][j-1] + matrix[i-1][j-1] - sum[i-1][j-1]; 
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return sum[row2+1][col2+1] - sum[row2+1][col1] - sum[row1][col2+1] + sum[row1][col1];
	}

	@Test
	public void defaultTest() {
		setMatrix(new int[][]{{3, 0, 1, 4, 2},{5, 6, 3, 2, 1},{1, 2, 0, 1, 5},{4, 1, 0, 1, 7},{1, 0, 3, 0, 5}});
		assertEquals(8, sumRegion(2, 1, 4, 3));
		assertEquals(11, sumRegion(1, 1, 2, 2));
		assertEquals(12, sumRegion(1, 2, 2, 4));
	}
	
	

}
