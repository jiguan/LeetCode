package practice.first.bit;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddBinary {
	public String addBinary(String a, String b) {
		char res = '0';
		int len1 = a.length(), len2 = b.length();
		int maxLen = Math.max(len1, len2);
		StringBuffer sb = new StringBuffer();
		for(int i=1;i<=maxLen;i++) {
			char c1 = i<=len1 ? a.charAt(len1 - i) : '0';
			char c2 = i<=len2 ? b.charAt(len2 - i) : '0';
			if(c1=='0' && c2=='0') {
				sb.insert(0, res);
				res = '0';
			} else if(c1=='0' || c2=='0') {
				if(res=='0') { // 1 + 0
					sb.insert(0, '1');
				} else { //1 + 1
					sb.insert(0, '0');
				}
			} else { // 1 + 1
				sb.insert(0, res);
				res = '1';
			}
		}
		if(res!='0') sb.insert(0, res);
		return sb.toString();
	}
	
	@Test
	public void test0() {
		String a = "11", b = "1";
		assertEquals("100", addBinary(a, b));
	}
	
	@Test
	public void test1() {
		String a = "10001", b = "1";
		assertEquals("10010", addBinary(a, b));
	}
	@Test
	public void test2() {
		String a = "", b = "";
		assertEquals("", addBinary(a, b));
	}
	
	@Test
	public void test3() {
		String a = "1", b = "111";
		assertEquals("1000", addBinary(a, b));
	}
}
