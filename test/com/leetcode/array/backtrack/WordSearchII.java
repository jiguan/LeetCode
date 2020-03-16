package com.leetcode.array.backtrack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * Given a 2D board and a word, find how many words in words exist in the grid. The word can be constructed from letters
 * of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 */

public class WordSearchII {
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.next[i] == null) {
                    node.next[i] = new TrieNode();
                }
                node = node.next[i];
            }
            node.word = word;
        }
        return root;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new LinkedList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // we cannot use returned boolean as a indicator since trie tree could go deeper
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '#'
                || node.next[board[i][j] - 'a'] == null) {
            return;
        }

        char c = board[i][j];
        node = node.next[c - 'a'];
        if (node.word != null) {
            res.add(node.word);
            // avoid duplicates, like duplicated characters in matrix
            node.word = null;
        }

        board[i][j] = '#';
        dfs(board, i + 1, j, node, res);
        dfs(board, i, j + 1, node, res);
        dfs(board, i, j - 1, node, res);
        dfs(board, i - 1, j, node, res);
        board[i][j] = c;
    }

    // pure backtrack
    public List<String> findWords0(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (String word : words) {
                    if (track(board, i, j, word.toCharArray(), 0)) {
                        res.add(word);
                    }
                }
            }
        }
        return new ArrayList<>(res);
    }

    private boolean track(char[][] board, int i, int j, char[] word, int index) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word[index] != board[i][j]
                || board[i][j] == '#') {
            return false;
        }

        if (++index == word.length) {
            return true;
        }
        boolean res = false;
        char temp = board[i][j];
        board[i][j] = '#';
        if (track(board, i + 1, j, word, index) || track(board, i, j + 1, word, index)
                || track(board, i, j - 1, word, index) || track(board, i - 1, j, word, index)) {
            res = true;
        }
        board[i][j] = temp;
        return res;
    }

    @Test
    public void test0() {
        char[][] board = new char[][]{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}};
        String[] words = new String[]{"oath", "pea", "eat", "rain"};
        List<String> expected = Arrays.asList("oath", "eat");
        assertEquals(expected, findWords(board, words));
    }

    @Test
    public void test1() {
        char[][] board = new char[][]{{'a'}};
        String[] words = new String[]{"a", "a"};
        List<String> expected = Arrays.asList("a");
        assertEquals(expected, findWords(board, words));
    }

    @Test
    public void test2() {
        char[][] board = new char[][]{{'a', 'b'}};
        String[] words = new String[]{"ba"};
        List<String> expected = Arrays.asList("ba");
        assertEquals(expected, findWords(board, words));
    }

}
