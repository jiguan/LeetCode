package com.leetcode.backtrack;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.PrettyPrint;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new LinkedList<>();
        backtrack(S.toCharArray(), 0, res);
        return res;
    }

    private void backtrack(char[] arr, int index, List<String> res) {
        if (index == arr.length) {
            res.add(new String(arr));
            return;
        }

        backtrack(arr, index + 1, res);

        if (Character.isAlphabetic(arr[index])) {
            arr[index] ^= (1 << 5);
            backtrack(arr, index + 1, res);
        }
    }

    @Test
    public void test0() {
        String S = "a1b2";
        List<String> res = letterCasePermutation(S);
        assertEquals(4, res.size());
        PrettyPrint.print(res);
    }
}
