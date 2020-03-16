package com.leetcode.array.permutation;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if (n > 0) {
            generate("", n, n, res);
        }
        return res;
    }

    private void generate(String cur, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(cur);
            return;
        }
        if (left > 0) {
            generate(cur + "(", left - 1, right, res);
        }
        if (left < right) {
            generate(cur + ")", left, right - 1, res);
        }
    }

    @Test
    public void test0() {
        Set<String> expected = new HashSet<>(Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
        assertEquals(expected, new HashSet<>(generateParenthesis(3)));
    }
}
