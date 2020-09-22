package com.interview.indeed;

import java.util.ArrayList;
import java.util.List;

public class AutoComplete {
    /*
    ��һ��string��list��Ϊ���ݿ⣬����һ��string���硰py�������һ��string��list��py��ͷ��string��list���⣺�Ѹ���string��list sort֮����binary search��
��������ˡ�������û�������bianry search����������ʾ��д�ˡ�

     */
    TrieNode root;
    public AutoComplete(String[] words) {
        root = new TrieNode();
        for (String word : words)
            add(root, word);
    }

    public void add(TrieNode root, String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next[c-'a'] == null)
                cur.next[c-'a'] = new TrieNode();
            cur = cur.next[c-'a'];
        }
        cur.word = word;
    }

    public List<String> findAllWords(String prefix) {
        List<String> res = new ArrayList<>();
        if (prefix == null || prefix.length() == 0)
            return res;

        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.next[c-'a'] == null)
                return null;
            cur = cur.next[c-'a'];
        }
        dfs(res, cur);
        return res;
    }

    private void dfs(List<String> res, TrieNode root) {
        if (root.word != null) {
            res.add(root.word);
        }
        // continue explore
        for (int i = 0; i < 26; i++) {
            if (root.next[i] != null) {
                dfs(res, root.next[i]);
            }
        }

    }

    public static void main(String[] args) {
        String[] words = {
                "marketing",
                "make",
                "stop",
                "development",
                "develop",
                "dev"
        };
        AutoComplete sol = new AutoComplete(words);
        System.out.println(sol.findAllWords("de"));
    }
    
    class TrieNode {
        String word;
        TrieNode[] next = new TrieNode[26];
    }
}


