package com.algorithm;

/*Key benefit: 26 ways trie tree has much more null links than Ternary Search Tree, situation gets worse when handling 256 characters
 * over Hashing: 
		1. Hashing needs to examine entire key
		2. Search hits and misses cost about the same
		3. Performance relies on hashing function 
		4. Doesn't support ordered symbol table operation
*/
public class TernaryTree {
	private TSTNode root;

	private class TSTNode {
		public char val;
		public boolean isEnd;
		// smaller, equals, larger than current data
		// if matched, go to middle link
		TSTNode left, middle, right;

		/** Constructor **/
		public TSTNode(char val) {
			this.val = val;
			this.isEnd = false;
			this.left = null;
			this.middle = null;
			this.right = null;
		}
	}

	public void add(String str) {
		root = add(root, str.toCharArray(), 0);
	}

	private TSTNode add(TSTNode node, char[] word, int index) {
		char c = word[index];
		if (node == null) {
			node = new TSTNode(c);
		}
		if (c < node.val) {
			node.left = add(node.left, word, index);
		} else if (c > node.val) {
			node.right = add(node.right, word, index);
		} else { // matched, find next one
			node.middle = add(node.middle, word, index + 1);
		}

		return node;
	}

	public void delete(String word) {
		delete(root, word.toCharArray(), 0);
	}

	/** function to delete a word **/
	private void delete(TSTNode node, char[] word, int index) {
		if (node == null)
			return;

		if (word[index] < node.val)
			delete(node.left, word, index);
		else if (word[index] > node.val)
			delete(node.right, word, index);
		else {
			/** to delete a word just make isEnd false **/
			if (node.isEnd && index == word.length - 1) {
				node.isEnd = false;
			} else if (index + 1 < word.length)
				delete(node.middle, word, index + 1);
		}
	}

	public boolean search(String word) {
		return search(root, word.toCharArray(), 0);
	}

	/** function to search for a word **/
	private boolean search(TSTNode node, char[] word, int index) {
		if (node == null)
			return false;

		if (word[index] < node.val)
			return search(node.left, word, index);
		else if (word[index] > node.val)
			return search(node.right, word, index);
		else {
			if (node.isEnd && index == word.length - 1)
				return true;
			else if (index == word.length - 1) {
				// partial word found
				return false;
			} else {
				return search(node.middle, word, index + 1);
			}
		}
	}

}
