package com.leetcode.backtrack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class SplitArrayIntoFibonacciSequence {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        backtrack(S, 0, res);
        return res;
    }

    private boolean backtrack(String S, int index, List<Integer> res) {
        if (res.size() >= 3 && index == S.length()) {
            return true;
        }

        for (int i = index + 1; i <= S.length(); i++) {
            String str = S.substring(index, i);
            if(str.length() > 1 && str.startsWith("0") || Long.parseLong(str) > Integer.MAX_VALUE) break;
            
            int num = Integer.parseInt(str);
            int len = res.size();
            if (len <= 1 || res.get(len - 2) + res.get(len - 1) == num) {
                res.add(num);
                if (backtrack(S, i, res)) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }
        return false;
    }

    @Test
    public void test0() {
        String S = "0123";
        List<Integer> res = splitIntoFibonacci(S);
        assertTrue(res.isEmpty());
    }

    @Test
    public void test1() {
        String S = "11235813";
        List<Integer> res = splitIntoFibonacci(S);
        List<Integer> expected = Arrays.asList(1, 1, 2, 3, 5, 8, 13);
        assertEquals(expected, res);
    }

    @Test
    public void test2() {
        String S = "123456579";
        List<Integer> res = splitIntoFibonacci(S);
        List<Integer> expected = Arrays.asList(123, 456, 579);
        assertEquals(expected, res);
    }
    
    @Test
    public void test3() {
        String S = "1011";
        List<Integer> res = splitIntoFibonacci(S);
        List<Integer> expected = Arrays.asList(1, 0, 1, 1);
        assertEquals(expected, res);
    }
}
