package com.leetcode.implement;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import org.junit.Test;

/*
 * 1. The hot degree for a sentence is defined as the number of times a user typed the exactly same
 * sentence before.
 * 
 * 2. The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest
 * one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller
 * one appears first).
 * 
 * 3. If less than 3 hot sentences exist, then just return as many as you can.
 * 
 * 4. When the input is a special character, it means the sentence ends, and in this case, you need
 * to return an empty list.
 */
public class DesignSearchAutocompleteSystem {

    @Test
    public void test0() {
        String[] sentences = {"i love you", "island", "ironman", "i love leetcode"};
        int[] times = {5, 3, 2, 2};
        AutocompleteSystem sys = new AutocompleteSystem(sentences, times);
        assertEquals(Arrays.asList("i love you", "island", "i love leetcode"), sys.input('i'));
        assertEquals(Arrays.asList("i love you", "i love leetcode"), sys.input(' '));
        assertEquals(new ArrayList<>(), sys.input('a'));
        assertEquals(new ArrayList<>(), sys.input('#'));
        sys.input('i');
        assertEquals(Arrays.asList("i love you", "i love leetcode", "i a"), sys.input(' '));
    }
}


class AutocompleteSystem {
    TrieNode root = new TrieNode();
    String prefix = "";

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; ++i) {
            add(sentences[i], times[i]);
        }
    }

    private void add(String sentence, int time) {
        TrieNode node = root;
        for (char ch : sentence.toCharArray()) {
            TrieNode next = node.nodes.get(ch);

            if (next == null) {
                next = new TrieNode();
                node.nodes.put(ch, next);
            }
            node = next;
            node.counts.put(sentence, node.counts.getOrDefault(sentence, 0) + time);
        }
        node.leaf = true;
    }

    // For each character they type except '#', you need to return the top 3
    public List<String> input(char c) {
        List<String> res = new ArrayList<String>();

        if (c == '#') {
            add(prefix, 1);
            prefix = "";
            return res;
        }

        prefix = prefix + c;
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            TrieNode next = node.nodes.get(ch);
            if (next == null) {
                return res;
            }
            node = next;
        }

        // low frequency to high, same frequency, reverse ASCII-code
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> (a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey())
                        : a.getValue() - b.getValue()));
        for (Map.Entry<String, Integer> entry : node.counts.entrySet()) {
            pq.add(entry);
            if (pq.size() > 3) pq.poll();
        }
        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> entry = pq.poll();
            // pq is reverse order
            res.add(0, entry.getKey());
        }
        return res;
    }
}


class TrieNode {
    Map<Character, TrieNode> nodes = new HashMap<>();
    Map<String, Integer> counts = new HashMap<>();
    boolean leaf = false;
}
