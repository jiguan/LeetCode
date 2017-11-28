package com.leetcode.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class ImplementTrie {
    @Test
    public void test0() {
        Trie trie = new Trie();
        trie.insert("a");
        assertTrue(trie.search("a"));
        assertTrue(trie.startsWith("a"));
    }

    @Test
    public void test1() {
        Trie trie = new Trie();
        trie.insert("abc");
        assertTrue(trie.search("abc"));
        assertFalse(trie.search("ab"));
        trie.insert("ab");
        assertTrue(trie.search("ab"));
        trie.insert("ab");
        assertTrue(trie.search("ab"));

    }
    
    @Test
    public void test2() {
        Trie trie = new Trie();
        trie.insert("app");
        assertTrue(trie.search("app"));
        trie.insert("apple");
        assertTrue(trie.search("app"));
    }

}


class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (char w : word.toCharArray()) {
            TrieNode tmp = node.nodes[w - 'a'];
            if (tmp == null) {
                tmp = new TrieNode();
                node.nodes[w - 'a'] = tmp;
            }
            node = tmp;
        }
        node.leaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode node = root;
        for (char w : word.toCharArray()) {
            node = node.nodes[w - 'a'];
            if (node == null)
                return false;
        }
        return node.leaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (char w : prefix.toCharArray()) {
            node = node.nodes[w - 'a'];
            if (node == null) {
                return false;
            }
        }
        return true;
    }
    
    class TrieNode {
        public TrieNode[] nodes = new TrieNode[26];
        public boolean leaf = false;

        // Initialize your data structure here.
        public TrieNode() {

        }

        @Override
        public String toString() {
            return "[nodes: " + Arrays.toString(nodes) + ", leaf: " + leaf + "]";
        }
    }
}




