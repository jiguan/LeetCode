package com.leetcode.palindrome;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

/*
 * Rather than use isPalindrome to check it every time, using an array to record
 * dp[i][j]== true means substring(i, j+1) is a valid palindrome
 */

public class PalindromePartitioning {
    public List<List<String>> partition0(String s) {
        List<List<String>> res = new LinkedList<>();
        dfs(s, 0, new LinkedList<>(), res);
        return res;
    }

    private void dfs(String s, int start, List<String> cur,
            List<List<String>> res) {
        if (start == s.length()) {
            res.add(new LinkedList<>(cur));
            return;
        }

        for (int i = start; i < s.length(); ++i) {
            if (isPalindrome(s, start, i)) {
                cur.add(s.substring(start, i + 1));
                dfs(s, i + 1, cur, res);
                cur.remove(cur.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            ++start;
            --end;
        }
        return true;
    }

    public List<List<String>> partition(String s) {
        List<List<String>> res = new LinkedList<>();
        boolean[][] matrix = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; --i) {
            for (int j = s.length() - 1; j >= i; --j) {
                // i == j, i == j+1, i == j + 2
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || matrix[i + 1][j - 1])) {
                    matrix[i][j] = true;
                }
            }
        }
        dfs(s, 0, matrix, new LinkedList<>(), res);
        return res;
    }

    private void dfs(String s, int start, boolean[][] matrix, List<String> current, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new LinkedList<String>(current));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (matrix[start][i]) {
                current.add(s.substring(start, i + 1));
                dfs(s, i + 1, matrix, current, res);
                current.remove(current.size() - 1);
            }
        }
    }

    @Test
    public void test0() {
        String s = "aab";
        List<List<String>> res = partition(s);
        for (List<String> list : res) {
            PrettyPrint.print(list);
        }
    }

    @Test
    public void test1() {
        String s = "abbab";
        List<List<String>> res = partition(s);
        for (List<String> list : res) {
            PrettyPrint.print(list);
        }
        assertEquals(4, res.size());
    }
}
