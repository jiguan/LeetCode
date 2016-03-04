package practice.first.word;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ImplementStrStr {
	public int strStr(String haystack, String needle) {
		if(haystack==null||needle==null) return -1;
		int h = haystack.length();
		int n = needle.length();
		if((h==0&&n==0)||n==0) return 0;
		
		for(int i=0;i<h-n+1;i++) {
			int j = 0;
			while(j<n && haystack.charAt(i+j)==needle.charAt(j)) {
				j++;
			}
			if(j==n) return i;
		}
		return -1;
	}
	
	@Test
	public void test0() {
		String haystack = "abc", needle = "bc";
		assertEquals(1, strStr(haystack, needle));
	}
	
	@Test
	public void test1() {
		String haystack = "abec", needle = "bc";
		assertEquals(-1, strStr(haystack, needle));
	}
	
	@Test
	public void test2() {
		String haystack = "", needle = "";
		assertEquals(0, strStr(haystack, needle));
	}
	
	@Test
	public void test3() {
		String haystack = "a", needle = "";
		assertEquals(0, strStr(haystack, needle));
	}
	
	@Test
	public void test4() {
		String haystack = "abbc", needle = "bb";
		assertEquals(1, strStr(haystack, needle));
	}
	
	@Test
	public void test5() {
		String haystack = "", needle = "bb";
		assertEquals(-1, strStr(haystack, needle));
	}
	
	@Test
	public void test6() {
		String haystack = "mississippi", needle = "issip";
		assertEquals(4, strStr(haystack, needle));
	}

}
