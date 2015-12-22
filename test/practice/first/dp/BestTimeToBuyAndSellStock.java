package practice.first.dp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BestTimeToBuyAndSellStock {
	public int maxProfit(int[] prices) {
		if (prices.length < 2)
			return 0;
		int min = prices[0];
		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			maxProfit = Math.max(maxProfit, prices[i] - min);
		}
		return maxProfit;
	}

	@Test
	public void defaultTest() {
		assertEquals(0, maxProfit(new int[] { 2, 1 }));
		assertEquals(4, maxProfit(new int[] { 1, 3, 2, 5, 3, 4 }));
		assertEquals(3, maxProfit(new int[] { 1, 4, 2 }));
		assertEquals(1, maxProfit(new int[] { 4, 1, 2 }));
	}
}
