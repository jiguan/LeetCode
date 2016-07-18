package com.leetcode.util;

public class TrieNode {
    private TrieNode[] nodes = new TrieNode[26];
    public boolean isEnd;

    public TrieNode getNode(Character ch) {
        return nodes[Character.toLowerCase(ch) - 'a'];
    }
    
    public void setNode(char ch, TrieNode node) {
        nodes[Character.toLowerCase(ch) - 'a'] = node;
    }
    
}
