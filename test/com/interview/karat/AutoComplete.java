package com.interview.karat;

import java.util.ArrayList;
import java.util.List;

public class AutoComplete {

    TrieNode trie = null;

    public static List<String> lookup(String s, TrieNode trie) {
        TrieNode node = trie;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                if (node != null && node.next[ch - 'A'] != null) {
                    node = node.next[ch - 'A'];
                    res = node.words;
                } else {
                    res = new ArrayList<>();
                }
            }
        }

        return res;
    }

    private static void printListStrign(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }

    public static TrieNode createTrie(String[] strs) {
        TrieNode root = new TrieNode();
        TrieNode node = root;
        for (String str : strs) {
            node = root;
            for (int i = 0; i < str.length(); i++) {
                Character ch = str.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    if (node.next[ch - 'A'] == null) {
                        node.next[ch - 'A'] = new TrieNode();
                    }
                    node = node.next[ch - 'A'];
                    node.words.add(str);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        String[] className = new String[] {"GraphView", "DataGraphView", "DataController",
                "GraphViewController", "DataScienceView"};

        TrieNode trie = createTrie(className);
        // printListStrign(lookup("Data", trie)); // {"DataGraphView", "DataController",
        // "DataScienceView"}
        // printListStrign(lookup("GVi", trie)); // {"GraphView", "GraphViewController"}

        printListStrign(lookup("GraphController", trie)); // {""}

        printListStrign(lookup("GraphV", trie)); // {""}
    }
}


class TrieNode {
    List<String> words = new ArrayList<>();
    TrieNode[] next = new TrieNode[26];
}
