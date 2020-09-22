package com.interview.pinterest;

/*
 * Date: Jul 07, 2018 Problem: Longest word in dictionary Difficulty: Easy Notes:
 * 
 * Given a list of strings words representing an English Dictionary, find the longest word in words
 * that can be built one character at a time by other words in words. If there is more than one
 * possible answer, return the longest word with the smallest lexicographical order.
 * 
 * If there is no answer, return the empty string. Example 1: Input: words = ["w","wo","wor","worl",
 * "world"] Output: "world" Explanation: The word "world" can be built one character at a time by
 * "w", "wo", "wor", and "worl". Example 2: Input: words = ["a", "banana", "app", "appl", "ap",
 * "apply", "apple"] Output: "apple" Explanation: Both "apply" and "apple" can be built from other
 * words in the dictionary. However, "apple" is lexicographically smaller than "apply". Note:
 * 
 * All the strings in the input will only contain lowercase letters. The length of words will be in
 * the range [1, 1000]. The length of words[i] will be in the range [1, 30].
 * 
 */

// 11ms 100%
// time O(n*wordLength) + O(V+E)
// space O(n*wordLength)

class LongestWordInDictionary {
    class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];

    }
    class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
            root.word = "";
        }

        public void insert(String s) {
            char[] sc = s.toCharArray();
            TrieNode cur = root;
            for (int i = 0; i < sc.length; i++) {
                if (cur.children[sc[i] - 'a'] == null) {
                    cur.children[sc[i] - 'a'] = new TrieNode();
                }
                cur = cur.children[sc[i] - 'a'];
            }
            cur.word = s;
        }
    }

    public String longestWord(String[] words) {
        Trie t = new Trie();
        for (String word : words) {
            t.insert(word);
        }
        String[] res = new String[] {""};
        dfs(t.root, res);
        return res[0];
    }

    public void dfs(TrieNode cur, String[] res) {
        if (cur.word == null) {
            return;
        }
        if (cur.word.length() > res[0].length()) {
            res[0] = cur.word;
        }
        for (TrieNode child : cur.children) {
            if (child != null)
                dfs(child, res);
        }
    }
}
