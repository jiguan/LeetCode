package com.leetcode.graph.dependency;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;


public class BeforeAndAfterPuzzle {
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        List<String> res = new ArrayList<String>();
        if (phrases == null || phrases.length < 2) return res;
        Set<String> set = new HashSet<>();
        Map<String, Set<String>> first = new HashMap<>(), last = new HashMap<>();
        Map<String, Integer> count = new HashMap<String, Integer>();
        for (String phrase : phrases) {
            String[] words = phrase.split("\\s+");
            String head = words[0], tail = words[words.length - 1];
            first.computeIfAbsent(head, s -> new HashSet<>()).add(phrase.substring(head.length()));
            last.computeIfAbsent(tail, s -> new HashSet<>())
                    .add(phrase.substring(0, phrase.length() - tail.length()));
            count.put(phrase, 1 + count.getOrDefault(phrase, 0));
        }

        for (String phrase : phrases) {
            String[] words = phrase.split("\\s+");
            String head = words[0], tail = words[words.length - 1];
            // single letter
            if (phrase.equals(head)) {
                if (count.get(phrase) > 1) {
                    set.add(phrase);
                }
            } else if (first.containsKey(tail)) { // there is a space
                for (String tmp : first.get(tail)) {
                    set.add(phrase + tmp);
                }
            }
        }
        res = new ArrayList<String>(set);
        Collections.sort(res);
        return res;
    }

    @Test
    public void test0() {
        String[] phrases = {"mission statement", "a quick bite to eat", "a chip off the old block",
                "chocolate bar", "mission impossible", "a man on a mission", "block party",
                "eat my words", "bar of soap"};
        List<String> expected = Arrays.asList("a chip off the old block party",
                "a man on a mission impossible", "a man on a mission statement",
                "a quick bite to eat my words", "chocolate bar of soap");
        assertEquals(expected, beforeAndAfterPuzzles(phrases));
    }

    @Test
    public void test1() {
        String[] phrases = {"writing code", "code rocks", "code sucks", "i like writing"};
        List<String> expected =
                Arrays.asList("i like writing code", "writing code rocks", "writing code sucks");
        assertEquals(expected, beforeAndAfterPuzzles(phrases));
    }

    @Test
    public void test2() {
        String[] phrases = {"a", "b a"};
        List<String> expected = Arrays.asList("a", "b a");
        assertEquals(expected, beforeAndAfterPuzzles(phrases));
    }

    @Test
    public void test3() {
        String[] phrases = {"a", "b", "a"};
        List<String> expected = Arrays.asList("a");
        assertEquals(expected, beforeAndAfterPuzzles(phrases));
    }
}
