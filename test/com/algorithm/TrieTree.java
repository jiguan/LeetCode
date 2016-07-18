package com.algorithm;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.leetcode.util.TrieNode;

public class TrieTree {
    private TrieNode root = new TrieNode();
    
    public void insert(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for(char ch : chars) {
            if(node.getNode(ch)==null) {
                node.setNode(ch, new TrieNode());
            } 
            node = node.getNode(ch);
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for(char ch : chars) {
            node = node.getNode(ch);
            if(node==null) return false;
        }
        return node.isEnd;
    }
    
    public boolean startsWith(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for(char ch : chars) {
            node = node.getNode(ch);
            if(node==null) return false;
        }
        return true;
    }
    
    @Test
    public void test0() {
        insert("add");
        assertTrue(search("add"));
        assertTrue(startsWith("ad"));
        assertFalse(search("adb"));
    }
    
}
