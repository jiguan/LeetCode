package com.leetcode.bfs;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import org.junit.Test;

public class RemoveInvalidParentheses {
    /*
     * In BFS we handle the states level by level, in the worst case, we need to handle all the
     * levels, we can analyze the time complexity level by level and add them up to get the final
     * complexity.
     * 
     * On the first level, there's only one string which is the input string s, let's say the length
     * of it is n, to check whether it's valid, we need O(n) time. On the second level, we remove
     * one ( or ) from the first level, so there are C(n, n-1) new strings, each of them has n-1
     * characters, and for each string, we need to check whether it's valid or not, thus the total
     * time complexity on this level is (n-1) x C(n, n-1). Come to the third level, total time
     * complexity is (n-2) x C(n, n-2), so on and so forth...
     * 
     * Finally we have this formula:
     * 
     * T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1).
     */

    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);
        visited.add(s);

        boolean found = false;
        while (!queue.isEmpty()) {
            s = queue.poll();

            // since s is valid, we don't need to remove any '(' or ')'
            if (isValid(s)) {
                res.add(s);
                found = true;
            }

            if (found) continue;

            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;
                String t = s.substring(0, i) + s.substring(i + 1);
                if (!visited.contains(t)) {
                    queue.add(t);
                    visited.add(t);
                }
            }
        }
        return res;
    }

    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }
        return count == 0;
    }

    @Test
    public void test0() {
        String s = "()())()";
        Set<String> expected = new HashSet<>(Arrays.asList("()()()", "(())()"));
        assertEquals(expected, new HashSet<>(removeInvalidParentheses(s)));
    }

    @Test
    public void test2() {
        String s = "";
        Set<String> expected = new HashSet<>(Arrays.asList(""));
        assertEquals(expected, new HashSet<>(removeInvalidParentheses(s)));
    }

    @Test
    public void test1() {
        String s = "()";
        String t = s.substring(0, 0);
        System.out.println(s + ".substring(0, 0) is " + t);
        String o = s.substring(0);
        System.out.println(s + ".substring(0) is " + o);
    }

}
