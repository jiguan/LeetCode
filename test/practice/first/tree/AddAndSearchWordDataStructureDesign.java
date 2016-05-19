package practice.first.tree;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

public class AddAndSearchWordDataStructureDesign {
    TrieNode root = new TrieNode();

    // Adds a word into the data structure.
    public void addWord(String word) {
        TrieNode node = root;
        char[] arr = word.toCharArray();
        for (char c : arr) {
            if (node.nodes[c - 'a'] == null) {
                node.nodes[c - 'a'] = new TrieNode();
            }
            node = node.nodes[c - 'a'];
        }
        node.leaf = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    private boolean search(TrieNode node, char[] word, int index) {
        if(index==word.length) return node.leaf;
        if(word[index]=='.') {
            for(TrieNode next : node.nodes) {
                if(next!=null) {
                    if(search(next, word, index+1)) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            TrieNode next = node.nodes[word[index] -'a'];
            if(next==null) return false;
            return search(next, word, index+1);
        }
    }


    class TrieNode {
        public TrieNode[] nodes = new TrieNode[26];
        public boolean leaf = false;

        @Override
        public String toString() {
            return "[nodes: " + Arrays.toString(nodes) + ", leaf: " + leaf + "]";
        }
    }

    @Test
    public void test0() {
        AddAndSearchWordDataStructureDesign struct = new AddAndSearchWordDataStructureDesign();
        struct.addWord("bad");
        struct.addWord("dad");
        struct.addWord("mad");
        assertFalse(struct.search("pad"));
        assertTrue(struct.search("bad"));
        assertTrue(struct.search(".ad"));
        assertTrue(struct.search("b.."));
    }
    
    @Test
    public void test1() {
        AddAndSearchWordDataStructureDesign struct = new AddAndSearchWordDataStructureDesign();
        struct.addWord("bad");
        assertFalse(struct.search("b"));
        assertTrue(struct.search("..."));
    }
}
