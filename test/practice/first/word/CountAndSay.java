package practice.first.word;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountAndSay {
	public String countAndSay(int n) {
		if(n<=0) return "0";
		String num = "1";
		for(int i=1;i<n;i++) {
			num = getNext(num);
		}
		return num;
	}
	
	private String getNext(String num) {
		int digit = num.length();
		StringBuffer buf = new StringBuffer();
		int i = 0;
		while(i<digit) {
			char cur = num.charAt(i); 
			int j = i+1;
			while(j<digit && num.charAt(j)==cur) {
				j++;
			}
			buf.append(j-i);
			buf.append(cur);
			i = j;
		}
		return buf.toString();
	}
	
	@Test
	public void test0() {
		int n = 1;
		assertEquals("1", countAndSay(n));
	}
	
	@Test
	public void test1() {
		int n = 2;
		assertEquals("11", countAndSay(n));
	}
	
	@Test
	public void test2() {
		int n = 5;
		assertEquals("111221", countAndSay(n));
	}
}
