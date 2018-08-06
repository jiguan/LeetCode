package com.interview.mustdo;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

public class KSimilarStrings {
	public int kSimilarity(String A, String B) {
		if (A.equals(B))
			return 0;
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		queue.add(A);
		visited.add(A);
		int res = 0;
		while (!queue.isEmpty()) {
			res++;
			for (int size = queue.size(); size > 0; size--) {
				String str = queue.poll();
				int i = 0;
				while (str.charAt(i) == B.charAt(i))
					i++;
				// str[i] != B[i], need to find j so that str[j] == B[i]
				for (int j = i + 1; j < str.length(); j++) {
					if (str.charAt(j) == B.charAt(j) || str.charAt(j) != B.charAt(i))
						continue;
					String tmp = swap(str, i, j);
					if (tmp.equals(B))
						return res;
					if (visited.add(tmp))
						queue.add(tmp);
				}
			}
		}
		return res;
	}

	private String swap(String str, int i, int j) {
		char[] arr = str.toCharArray();
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
		return String.valueOf(arr);
	}

	@Test
	public void test0() {
		String A = "abac", B = "baca";
		assertEquals(2, kSimilarity(A, B));
	}
}
