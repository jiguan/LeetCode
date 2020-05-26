package com.leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Group Shifted Strings
 * 
 * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" ->
 * "bcd". We can keep "shifting" which forms the sequence:
 * 
 * "abc" -> "bcd" -> ... -> "xyz" Given a list of strings which contains only lowercase alphabets,
 * group all strings that belong to the same shifting sequence.
 * 
 * Example:
 * 
 * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], Output: [ ["abc","bcd","xyz"],
 * ["az","ba"], ["acef"], ["a","z"] ]
 */
public class GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return Collections.emptyList();
        }
        Map<String, List<String>> store = new HashMap<>();
        for (String s : strings) {
            String min = makeFirstLetterA(s);
            if (!store.containsKey(min)) {
                store.put(min, new ArrayList<String>());
            }
            store.get(min).add(s);
        }
        List<List<String>> res = new ArrayList<>();
        for (List<String> list : store.values()) {
            res.add(list);
        }
        return res;
    }
    
    private String makeFirstLetterA(String s) {
        char first = s.charAt(0);
        int diff = first - 'a';
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur < first) {
                cur = (char)(cur + 26 - diff);
            } else {
                cur = (char)(cur - diff);
            }
            sb.append(cur);
        }
        return sb.toString();
    }
}
