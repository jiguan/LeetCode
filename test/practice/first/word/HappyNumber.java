package practice.first.word;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class HappyNumber {
	
	public boolean isHappy(int n) {
		Set<Integer> occurs = new HashSet<>();
		int squareSum, remain;
		while(occurs.add(n)) {
			squareSum = 0;
			while(n > 0) {
				remain = n % 10;
				squareSum += remain * remain;
				n /= 10;
			}
			n = squareSum;
			if(n==1)  return true;
		}
		return false;
	}
	
	@Test
	public void test0() {
		int num = 7;
		assertTrue(isHappy(num));
	}
}
