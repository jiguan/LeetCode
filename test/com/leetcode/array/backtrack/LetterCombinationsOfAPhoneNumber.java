package com.leetcode.array.backtrack;

import static org.junit.Assert.assertEquals;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class LetterCombinationsOfAPhoneNumber {
    private final static String[] map =
            new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new LinkedList<>();
        dfs(digits.toCharArray(), 0, sb, res);
        return res;
    }

    private void dfs(char[] digits, int index, StringBuilder sb, List<String> res) {
        if (index == digits.length) {
            res.add(sb.toString());
            return;
        }
        int num = digits[index] - '0';
        for (char letter : map[num].toCharArray()) {
            sb.append(letter);
            dfs(digits, index + 1, sb, res);
            sb.setLength(sb.length() - 1);
        }
    }

    @Test
    public void test0() {
        String digits = "23";
        Set<String> res = new HashSet<>(letterCombinations(digits));
        assertEquals(9, res.size());
    }
}
