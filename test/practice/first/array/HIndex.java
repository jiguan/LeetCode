package practice.first.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class HIndex {
	public int hIndex(int[] citations) {
		Arrays.sort(citations);
		int res = citations.length;
		for (int cite : citations) {
			if (cite < res)
				res--;
		}
		return res;
	}

	@Test
	public void test1() {
		int[] citations = new int[] { 3, 0, 6, 1, 5 };
		System.out.println(hIndex(citations));
	}

	@Test
	public void test2() {
		int[] citations = new int[] { 3 };
		assertEquals(1, hIndex(citations));
	}

	@Test
	public void test3() {
		int[] citations = new int[] { 0 };
		assertEquals(0, hIndex(citations));
	}
	
	@Test
	public void test5() {
		int[] citations = new int[] { 1 };
		assertEquals(1, hIndex(citations));
	}

	@Test
	public void test4() {
		int[] citations = new int[] { 0, 1 };
		assertEquals(1, hIndex(citations));
	}
}
