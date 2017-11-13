package com.leetcode.tree;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.leetcode.util.TreeNode;

public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        visit(root, res, 0);
        return res;
    }
    
    private void visit(TreeNode node, List<Integer> res, int level) {
        if(node==null) return;
        // if this is the first node in this row
        if(level == res.size()) {
            res.add(node.val);
        } else {
            res.set(level, Math.max(res.get(level), node.val));
        }
        visit(node.left, res, level+1);
        visit(node.right, res, level+1);
    }
    
    /*
     *      1
     *     / \
     *    3   2
     *   / \   \  
     *  5   3   9 
     */
    @Test
	public void test0() {
    	TreeNode root = new TreeNode(1);
    	TreeNode node1 = new TreeNode(3);
    	TreeNode node2 = new TreeNode(2);
    	TreeNode node3 = new TreeNode(5);
    	TreeNode node4 = new TreeNode(3);
    	TreeNode node5 = new TreeNode(9);
    	
    	root.left = node1;
    	root.right = node2;
    	node1.left = node3;
    	node1.right = node4;
    	node2.right = node5;
    	
    	List<Integer> expected = Arrays.asList(1,3,9);
    	assertEquals(expected, largestValues(root));
	}
}
