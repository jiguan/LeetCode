package practice.first.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SqrtX {
	public int mySqrt(int x) {
		if(x<=1) return x;
		int left = 1, right = x;
		while(left<right) {
		    int mid = (right - left >> 1) + left;
		    // mid * mid > x
		    if(mid > x / mid) {
		        right = mid;
		    } else {
		        left = mid + 1;
		    }
		}
		return left - 1;
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
	
	@Test
    public void test2() {
	    int a = 1, b = 9;
	    int mid = b - a >> 1 + a;
	    assertEquals(5, mid);
	}
	
}