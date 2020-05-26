package com.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/*
 * Serialize and Deserialize N-ary Tree
 * 
 * Serialization is the process of converting a data structure or object into a sequence of bits so
 * that it can be stored in a file or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in
 * which each node has no more than N children. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree
 * can be serialized to a string and this string can be deserialized to the original tree structure.
 * 
 * For example, you may serialize the following 3-ary tree
 * @formatter:off
 *        1
 *    /   |   \
 *   3    2    4
 *  / \
 * 5   6
 * @formatter:on
 * as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow
 * this format.
 * 
 * Or you can follow LeetCode's level order traversal serialization format, where each group of
 * children is separated by the null value.
 *         
 * @formatter:off
 *           1
 *     /   /  \    \
 *    2   3    4    5
 *      / \    |   / \
 *     6   7   8  9   10
 *         |   |  |
 *         11  12 13
 *         |
 *         14
 * @formatter:on
 * For example, the above tree may be serialized as
 * [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].
 * 
 * You do not necessarily need to follow the above suggested formats, there are many more different
 * formats that work so please be creative and come up with different approaches yourself.
 */
public class SerializeAndDeserializeNAryTree {

}


class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        _serialize(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null)
            return null;
        return _deserialize(data.toCharArray(), new int[] {0});
    }

    private void _serialize(Node root, StringBuilder sb) {
        if (root == null)
            return;
        sb.append("[").append(root.val);
        if (root.children != null) {
            for (Node child : root.children) {
                _serialize(child, sb);
            }
        }
        sb.append("]");
    }

    private Node _deserialize(char[] s, int[] p) {
        if (p[0] >= s.length)
            return null;
        int j = p[0] + 1;
        int val = 0;
        while (j < s.length && s[j] >= '0' && s[j] <= '9') {
            val = val * 10 + (s[j] - '0');
            j++;
        }
        Node root = new Node(val, new ArrayList<>());
        p[0] = j;
        while (s[p[0]] == '[') {
            root.children.add(_deserialize(s, p));
        }
        p[0] += 1;
        return root;
    }
    
    class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
