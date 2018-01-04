package com.leetcode.string;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class ReverseVowelsOfAString {
    public String reverseVowels0(String s) {
        if (s == null || s.length() < 2) return s;
        char[] chars = s.toCharArray();
        Set<Character> vowels = new HashSet<>(Arrays.asList('A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'));

        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (vowels.contains(c)) {
                stack.push(c);
            }
        }
        if (stack.isEmpty()) return s;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (vowels.contains(c)) {
                chars[i] = stack.pop();
            }
        }
        return new String(chars);
    }

    public String reverseVowels(String s) {
        int i = 0, j = s.length() - 1;
        char[] arr = s.toCharArray();
        Set<Character> vowels = new HashSet<>(Arrays.asList('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o', 'u'));
        while (i < j) {
            while (i < j && !vowels.contains(arr[i])) {
                ++i;
            }
            while (i < j && !vowels.contains(arr[j])) {
                --j;
            }
            if (i < j) {
                char tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                ++i;
                --j;
            }

        }
        return new String(arr);
    }

    @Test
    public void test0() {
        String s = "leetcode";
        assertEquals("leotcede", reverseVowels(s));
    }

    @Test
    public void test1() {
        String s = ",.";
        assertEquals(",.", reverseVowels(s));
    }
}
