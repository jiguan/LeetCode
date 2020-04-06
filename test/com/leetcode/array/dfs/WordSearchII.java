package com.leetcode.array.dfs;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;

public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        Trie root = buildTrieTree(words);
        List<String> res = new ArrayList<String>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                dfs(board, i, j, root, res);
            }
        }
        return res;
    }

    private void dfs(char[][] board, int i, int j, Trie node, List<String> res) {
        char ch = board[i][j];
        if (ch == '#' || node.nodes[ch - 'a'] == null) return;
        node = node.nodes[ch - 'a'];

        if (node.word != null) {
            res.add(node.word);
            node.word = null;
        }

        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j, node, res);
        if (j > 0) dfs(board, i, j - 1, node, res);
        if (i < board.length - 1) dfs(board, i + 1, j, node, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, node, res);
        board[i][j] = ch;
    }

    private Trie buildTrieTree(String[] words) {
        Trie root = new Trie();
        for (String word : words) {
            build(root, 0, word);
        }
        return root;
    }

    private void build(Trie node, int index, String word) {
        if (index == word.length()) {
            node.word = word;
            return;
        }

        char ch = word.charAt(index);
        if (node.nodes[ch - 'a'] == null) {
            node.nodes[ch - 'a'] = new Trie();
        }
        build(node.nodes[ch - 'a'], index + 1, word);
    }

    @Test
    public void test0() {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};
        Set<String> expected = new HashSet<>(Arrays.asList("eat", "oath"));
        assertEquals(expected, new HashSet<>(findWords(board, words)));
    }

    @Test
    public void test1() {
        char[][] board = {{'a', 'b'}};
        String[] words = {"ba"};
        Set<String> expected = new HashSet<>(Arrays.asList("ba"));
        assertEquals(expected, new HashSet<>(findWords(board, words)));
    }
}


class Trie {
    Trie[] nodes = new Trie[26];
    String word = null;
}
