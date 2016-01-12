package practice.first.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PerfectSquares {
	public int numSquares(int n) {
		while (n % 4 == 0)
			n /= 4;
		if (n % 8 == 7)
			return 4;
		for (int a = 0; a * a <= n; a++) {
			int b = (int) Math.sqrt((double) (n - a * a));
			if(b * b == n) return 1;
			if((a * a + b * b) == n) {
				return 2;
			}
		}
		return 3;
	}

	@Test
	public void test1() {
		int n = 4;
		assertEquals(1, numSquares(n));
	}

}
