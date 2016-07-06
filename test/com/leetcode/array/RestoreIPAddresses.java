package com.leetcode.array;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class RestoreIPAddresses {
	public List<String> restoreIpAddresses(String s) {
		List<String> res = new LinkedList<>();
		dfs(0, s, new String[4], 0, res);
		return res;
	}

	private void dfs(int start, String s, String[] current, int index, List<String> res) {
		if (start >= s.length() || index > 3) {
			if (index == 4 && start == s.length())
				res.add(String.join(".", current));
			return;
		}
		for (int end = start+1; end<= s.length(); end++) {
			if(end<s.length() - (3-index)*3) continue;
			String cand = s.substring(start, end);
			if (!cand.startsWith("0") && cand.length()<4 && Integer.parseInt(cand) <= 255) {
				current[index] = cand;
				dfs(end, s, current, index + 1, res);
			}
		}
	}
	
	@Test
	public void test0() {
		String s = "25525511135";
		List<String> result = restoreIpAddresses(s);
		PrettyPrint.print(result);
	}
}
