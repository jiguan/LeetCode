package com.interview.oci;

import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class CompressFile {
    @Test
    public void test0() {
        String[] lines = {"123 456 789", "987 654 321"};
        Util util = new Util();
        util.compress(lines);
        assertArrayEquals(lines, util.depress());
    }

    @Test
    public void test1() {
        String[] lines = {"123 456 789", "123 456"};
        Util util = new Util();
        util.compress(lines);
        assertArrayEquals(lines, util.depress());
    }


    private class Util {
        int size = 0;
        Trie trie = new Trie();

        public String[] depress() {
            String[] res = new String[size];
            for (int i = 0; i < size; ++i) {
                res[i] = trie.get(i);
            }
            return res;
        }

        public void compress(String[] lines) {
            size = lines.length;
            for (int i = 0; i < lines.length; ++i) {
                trie.insert(i, lines);
            }
        }
    }

    private class TrieNode {
        // line # - next word's node
        Map<Integer, TrieNode> map = new HashMap<>();
        String word;

        TrieNode(String word) {
            this.word = word;
        }
    }

    private class Trie {
        TrieNode root = new TrieNode(null);

        void insert(int index, String[] lines) {
            String line = lines[index];
            String[] words = line.split(" ");
            TrieNode curr = root;
            TrieNode next = null;
            for (String word : words) {
                // get next word in this line
                next = curr.map.getOrDefault(index, new TrieNode(word));
                curr.map.put(index, next);
                curr = next;
            }
        }

        String get(int index) {
            List<String> list = new ArrayList<>();
            TrieNode node = root;
            while (node.map.containsKey(index)) {
                TrieNode next = node.map.get(index);
                list.add(next.word);
                node = next;
            }
            return String.join(" ", list);
        }

    }

}
