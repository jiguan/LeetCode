package com.interview.mustdo;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class RemoveInvalidParentheses {
    /*
     * The program only generates valid answers. Every path in the search generates one valid
     * answer. The whole search space is a tree with k leaves. The number of nodes in the tree is
     * roughly O(k). But this is not always true, for example a degenerated tree. To generate one
     * node it requires O(n) time from the string concatenation among other things. So roughly
     * O(nk). Accurately O(nm) where m is the total "number of recursive calls" or
     * "nodes in the search tree". Then you need to relate m to n in the worst case. I wouldn't
     * worry too much about the accurate complexity analysis of this problem. It would require more
     * mathematics than an interview cares.
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        removeHelper(s, res, 0, 0, '(', ')');
        return res;
    }

    private void removeHelper(String s, List<String> res, int slow, int fast, char open,
            char close) {
        int count = 0;
        for (int end = fast; end < s.length(); ++end) {
            if (s.charAt(end) == open)
                count++;
            else if (s.charAt(end) == close) count--;
            // there is invalid parenthesis
            if (count < 0) {
                // traverse all possibilities
                for (int i = slow; i <= end; i++) {
                    // skip duplicates, if s.charAt(i-1) == close, it has been handled
                    if (s.charAt(i) == close && (i == slow || s.charAt(i - 1) != close)) {
                        removeHelper(s.substring(0, i) + s.substring(i + 1), res, i, end, open,
                                close);
                    }
                }
                // no need to continue, since current substring is invalid
                return;
            }
        }
        // there is no invalid ')'
        String reversed = new StringBuilder(s).reverse().toString();
        // check opposite direction
        if (open == '(') {
            removeHelper(reversed, res, 0, 0, ')', '(');
        } else {
            // second time, reversed back and add to res
            res.add(reversed);
        }
    }

    @Test
    public void test0() {
        String s = "(a)())()";
        Set<String> expected = new HashSet<>(Arrays.asList("(a)()()", "(a())()"));
        assertEquals(expected, new HashSet<>(removeInvalidParentheses(s)));
    }

    @Test
    public void test1() {
        String s = "(()(()";
        Set<String> expected = new HashSet<>(Arrays.asList("()()", "(())"));
        assertEquals(expected, new HashSet<>(removeInvalidParentheses(s)));
    }
}
