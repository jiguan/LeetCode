package com.interview.onsite.microsoft;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilenameRegexGenerator {

	public List<String> find(String str1, String str2) {
		List<String> res = new ArrayList<>();
		find(str1, str2, 0, res);
		return res;
	}

	private void find(String str1, String str2, int index, List<String> res) {
		if (str1.equals(str2)) {
			res.add(String.format("ls %s", str1));
			return;
		}
		index = getIndex(str1, str2, index);
		String lower = getLower(str1, index);
		String upper = getUpper(str1, index);
		// handle str1=1200 str2=1299 situation, generate 12* directly
		if (lower.equals(str1) && Integer.valueOf(upper) <= Integer.valueOf(str2)) {
			res.add(String.format("ls %s*", str1.substring(0, index)));
		} else {
			// handle str1=1234 str2=1239
			if (index == str1.length() - 1) {
				int start = str1.charAt(index) - '0';
				int end = str2.charAt(index) - '0';
				for (int i = start; i <= end; ++i) {
					res.add(String.format("ls %s", str1.substring(0, index) + i));
				}
			} else {
				// handle str1=1234 str2=1249
				upper = getUpper(str1, index + 1);
				// split into str1=1234 and str2=1239
				find(str1, upper, index, res);
				// str1=1240 and str2=1249
				find(String.valueOf(Integer.valueOf(upper) + 1), str2, index, res);
			}
		}
	}

	private String getLower(String str, int index) {
		StringBuilder sb = new StringBuilder();
		sb.append(str.subSequence(0, index));
		while (sb.length() < str.length()) {
			sb.append('0');
		}
		return sb.toString();
	}

	private String getUpper(String str, int index) {
		StringBuilder sb = new StringBuilder();
		sb.append(str.subSequence(0, index));
		while (sb.length() < str.length()) {
			sb.append('9');
		}
		return sb.toString();
	}

	private int getIndex(String str1, String str2, int index) {
		while (index < str1.length() && index < str2.length()) {
			if (str1.charAt(index) != str2.charAt(index))
				break;
			index++;
		}
		return index;
	}

	@Test
	public void test0() {
		String str1 = "12200";
		String str2 = "12599";
		List<String> expected = Arrays.asList("ls 122*", "ls 123*", "ls 124*", "ls 125*");
		List<String> actual = find(str1, str2);
		assertEquals(expected, actual);
	}

	@Test
	public void test1() {
		String str1 = "12345";
		String str2 = "12732";
		List<String> expected = Arrays.asList("ls 12345", "ls 12346", "ls 12347", "ls 12348", "ls 12349", "ls 1235*",
				"ls 1236*", "ls 1237*", "ls 1238*", "ls 1239*", "ls 124*", "ls 125*", "ls 126*", "ls 1270*", "ls 1271*",
				"ls 1272*", "ls 12730", "ls 12731", "ls 12732");
		List<String> actual = find(str1, str2);
		assertEquals(expected, actual);
	}
}
