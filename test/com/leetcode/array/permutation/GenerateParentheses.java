package com.leetcode.array.permutation;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n > 0) {
            char[] curr = new char[n * 2];
            generate(curr, 0, n, n, res);
        }
        return res;
    }

    private void generate(char[] curr, int i, int left, int right, List<String> res) {
        if (i == curr.length) {
            res.add(String.valueOf(curr));
            return;
        }

        if (left > 0) {
            curr[i] = '(';
            generate(curr, i + 1, left - 1, right, res);
        }

        if (left < right) {
            curr[i] = ')';
            generate(curr, i + 1, left, right - 1, res);
        }

    }

    @Test
    public void test0() {
        Set<String> expected =
                new HashSet<>(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        assertEquals(expected, new HashSet<>(generateParenthesis(3)));
    }
}
