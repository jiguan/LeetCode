package com.leetcode.array.stack;

import static org.junit.Assert.assertEquals;
import java.util.Stack;
import org.junit.Test;

/*
 * Remove Duplicate Letters
 * 
 * Given a string which contains only lowercase letters, remove duplicate letters so that every
 * letter appears once and only once. You must make sure your result is the smallest in
 * lexicographical order among all possible results.
 * 
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<Character>();

        // appearance count
        int[] count = new int[26];
        // used or not;

        // count appearances
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        boolean[] visited = new boolean[26];

        // go through each char
        for (char c : s.toCharArray()) {
            count[c - 'a']--;
            if (visited[c - 'a'])
                continue;

            // pop out the char which is bigger and still has some left in behind
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }
            // add current one
            stack.push(c);
            visited[c - 'a'] = true;
        }

        // move from stack to string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    @Test
    public void test0() {
        String s = "bcabc";
        assertEquals("abc", removeDuplicateLetters(s));
    }

}
