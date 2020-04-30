# Tree

## Tree traversal

```text
     3
    / \      Pre-order: 3 4 1 5 7
   4   7     Post-order: 1 5 4 7 3
  / \        In-order: 1 4 5 3 7
 1   5
```

### Pre-Order (root, left, right)

```java
public List<Integer> preorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    if (root == null) return res;
    // using stack to make sure root's left children is traversed first
    // use stack to accomplish recursive effect
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);

    while (!stack.isEmpty()) {
        TreeNode node = stack.pop();
        res.add(node.val);

        if (node.right != null) {
            stack.push(node.right);
        }
        if (node.left != null) {
            stack.push(node.left);
        }
    }
    return res;
}
```

### Post-order (left, right, root)

```java
public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    if (root == null) return res;

    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while(!stack.isEmpty()) {
        TreeNode node = stack.pop();
        res.add(0, node.val);
        if(node.left!=null) {
            stack.push(node.left);
        }
        if(node.right!=null) {
            stack.push(node.right);
        }
    }
    return res;
}
```

### In-order (left, root, right)

```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    // store current node's left children
    // Initially it is unknown
    Stack<TreeNode> stack = new Stack<>();
    TreeNode node = root;

    while (node != null || !stack.isEmpty()) {
        // as long as there is a valid node, we need to explore its left children
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        node = stack.pop();
        // all its left children have been traversed
        res.add(node.val);
        node = node.right;
    }

    return res;
}
```

**Pre-order and post-order are very similar** [Code](https://github.com/jiguan/LeetCode/blob/master/src/com/leetcode/util/Tree.java)

| Pre-order        | Post-order      |
| ---------------- | --------------- |
| Push right first | Push left first |
| Add to result    | Reverse result  |

## Questions

### Tree traverse

To flatten a binary tree, using *recursive* function and return a `Pair` to record the head and tail

- [Flatten Binary Tree to Linked List preorder](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)
- Flatten Binary Tree to Linked List inorder

*InOrder* traverse is like a binary search, the __root__ value is in the middle. But we can know the __left subtree size__ and __right subtree size__. From *PreOrder* and *PostOrder* we can know the root value directly

- [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) Use *InOrder* traverse and count visited nodes
- [Construct Binary Tree from PreOrder and InOrder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- [Construct Binary Tree from InOrder and PostOrder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)

### Lowest Common Ancestor

Find **ALL** ancestors of one node, then get the other's ancestor one by one and check if it has been visited already

- [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
- [Lowest Common Ancestor of Deepest Leaves](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/)
- [Maximum Difference Between Node and Ancestor](https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/)
- [Karat] Ancestors

- [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)

### Build BST to find number of elements

- [Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/)

### BST InOrder traversal generates a sorted array

- [Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/)

### Tree nodes/depth counting

  1. [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/) Try to solve it using recursive.
  1. [Find Bottom Left Tree Value](https://leetcode.com/problems/find-bottom-left-tree-value/description/)

### BFS and DFS

- [Average of Levels in Binary Tree](https://leetcode.com/problems/average-of-levels-in-binary-tree/description/)
- [Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/)

### Segment Tree

https://www.youtube.com/watch?v=ZBHKZF5w4YU

