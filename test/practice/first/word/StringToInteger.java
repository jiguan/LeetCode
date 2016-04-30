package practice.first.word;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringToInteger {
	public int myAtoi(String str) {
		int result = 0;
		if(str.length()==0) return result;
		char[] arr = str.toCharArray();
		int i = 0;
		if(arr[0]=='-'||arr[0]=='+') {
			i=1;
		}
		for(;i<str.length();i++) {
			char c = arr[i];
			int tmp = c - '0';
			if(tmp<0 || tmp>9) break;
			result = result * 10 + tmp;
		}
		if(arr[0]=='-') result = -result;
		return result;
	}
	
	@Test
	public void test0() {
		String str = "10";
		assertEquals(10, myAtoi(str));
	}
	
	@Test
	public void test1() {
		String str = "0";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test2() {
		String str = "-10";
		assertEquals(-10, myAtoi(str));
	}
	
	@Test
	public void test3() {
		String str = "-0";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test5() {
		String str = "+";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test4() {
		String str = "";
		assertEquals(0, myAtoi(str));
	}
	
	@Test
	public void test6() {
		String str = "+100";
		assertEquals(100, myAtoi(str));
	}
	
	@Test
	public void test7() {
		String str = "+-2";
		assertEquals(0, myAtoi(str));
	}
}
