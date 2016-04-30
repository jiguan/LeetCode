package practice.first.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SqrtX {
	public int mySqrt(int x) {
		if(x==0) return 0;
		int i = x;
		int count = 0;
		while(i < x / i) {
			count++;
			i = (i + x / i) / 2;     
		}
		System.out.println(count);
		return i;
	}
	
	@Test
	public void test0() {
		int x = 9;
		assertEquals(3, mySqrt(x));
	}
	
	@Test
	public void test1() {
		int x = 25;
		assertEquals(5, mySqrt(x));
	}
}