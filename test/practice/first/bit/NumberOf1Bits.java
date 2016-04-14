package practice.first.bit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumberOf1Bits {
	public int hammingWeight(int n) {
		int result = 0;
		for(int i=0;i<32;i++) {
			if((n & 1)==1) {
				result++;
			}
			n = n >> 1;
		}
		return result;
	}

	@Test
	public void test0() {
		int n = 11;
		assertEquals(3, hammingWeight(n));
	}
	
	@Test
	public void test1() {
		int n = 0;
		assertEquals(0, hammingWeight(n));
	}
	
	@Test
	public void test2() {
		int n = -2;
		assertEquals(2, hammingWeight(n));
	}
	
	@Test
	public void test3() {
		int n = 4;
		assertEquals(1, hammingWeight(n));
	}
	
	
}
