package practice.first.array;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class HIndex {
	public int hIndex0(int[] citations) {
		Arrays.sort(citations);
		int res = citations.length;
		for (int cite : citations) {
			if (cite < res)
				res--;
		}
		return res;
	}

	public int hIndex(int[] citations) {
		int n = citations.length, tot = 0;
		int[] arr = new int[n+1];
		for (int i = 0; i < n; i++) {
			if (citations[i] >= n)
				arr[n]++;
			else
				arr[citations[i]]++;
		}
		for (int i = n; i >= 0; i--) {
			tot += arr[i];
			if (tot >= i)
				return i;
		}
		return 0;
	}

	@Test
	public void test7() {
		int[] citations = new int[] { 5, 5, 5, 5, 5 };
		System.out.println(hIndex(citations));
	}
	
	@Test
	public void test8() {
		int[] citations = new int[] {1,2,3,4,5 };
		System.out.println(hIndex(citations));
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

	@Test
	public void test6() {
		int[] citations = new int[] { 1, 0 };
		assertEquals(1, hIndex(citations));
	}
}
