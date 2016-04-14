package practice.first.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountPrimes {
	public int countPrimes(int n) {
		if(n<3) return 0;
		boolean[] nums = new boolean[n];
		for(int i=3;i<n;i=i+2) {
			for(int j=3;j<=i;j=j+2) {
				long mul = i*j;
				if(mul>=n) break;
				nums[i*j] = true;
			}
		}
		int count = 1;
		for(int i=3;i<n;i=i+2) {
			if(nums[i]==false) {
				count++;
			}
		}
		return count;
	}
	@Test
	public void test0() {
		assertEquals(0, countPrimes(2));
	}
	@Test
	public void test1() {
		assertEquals(1, countPrimes(3));
	}
	@Test
	public void test2() {
		assertEquals(2, countPrimes(5));
	}
	@Test
	public void test3() {
		assertEquals(4, countPrimes(11));
	}
	@Test
	public void test4() {
		assertEquals(8, countPrimes(21));
	}
	
}
