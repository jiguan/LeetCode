package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * Brace Expansion
 * 
 * A string S represents a list of words.
 * 
 * Each letter in the word has 1 or more options. If there is one option, the letter is represented
 * as is. If there is more than one option, then curly braces delimit the options. For example,
 * "{a,b,c}" represents options ["a", "b", "c"].
 * 
 * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
 * 
 * Return all words that can be formed in this manner, in lexicographical order.
 * 
 * Example 1:
 * 
 * Input: "{a,b}c{d,e}f" Output: ["acdf","acef","bcdf","bcef"] Example 2:
 * 
 * Input: "abcd" Output: ["abcd"]
 */
public class BraceExpansion {
    public String[] expand(String s) {
        List<String> result = new ArrayList<>();
        // Convert string to list of list of chars
        List<List<String>> input = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '{') {
                int closeIndex = s.indexOf('}', i);
                String sub = s.substring(i + 1, closeIndex);
                String[] chars = sub.split(",");
                List<String> subList = new ArrayList<>(Arrays.asList(chars));
                input.add(subList);
                i = closeIndex + 1;
            } else {
                // parse till you encounter a bracket
                int j = i;
                while (j < s.length() && s.charAt(j) != '{') {
                    j++;
                }
                input.add(new ArrayList<>(Arrays.asList(s.substring(i, j))));
                i = j;
            }
        }
        helper(input, 0, new StringBuilder(), result);
        Collections.sort(result);
        String[] output = new String[result.size()];
        for (int j = 0; j < result.size(); j++) {
            output[j] = result.get(j);
        }
        return output;
    }

    private void helper(List<List<String>> input, int start, StringBuilder sb,
            List<String> result) {
        if (start == input.size()) {
            result.add(sb.toString());
            return;
        }
        List<String> cur = input.get(start);
        int len = sb.length();
        for (String temp : cur) {
            sb.append(temp);
            helper(input, start + 1, sb, result);
            sb.setLength(len);
        }
    }
}
