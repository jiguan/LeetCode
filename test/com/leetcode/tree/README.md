# Tree

## Tree traversal

        3       Pre-order: 3 4 1 5 7
      4   7     Post-order: 1 5 4 7 3
    1  5        In-order: 1 4 5 3 7

- Recursive solution is trivial. But it should be the first approach
- Iteration is tricky
- Pre-order: Easy. [Code](https://github.com/jiguan/LeetCode/blob/master/test/com/leetcode/tree/BinaryTreePreorderTraversal.java)
        1. Push root to stack 
        2. Inside while loop, pop out the node, and save its value to result list 
        3. If node has left, push right to stack, so as left node  
  - Post-order: Tricky. Similar to pre-order. [Code](https://github.com/jiguan/LeetCode/blob/master/test/com/leetcode/tree/BinaryTreePostorderTraversal.java)
        1. Push the root to the stack 
        2. Pop up the stack, if node'e left is not null, add left to the stack, so as the right node. Record the node's val 
        3. **Outside the while loop, reverse res**
  - In-order: Tricky. [Code](https://github.com/jiguan/LeetCode/blob/master/test/com/leetcode/tree/BinaryTreeInorderTraversal.java)
        1. Create a node variable outside while loop and do not push node to the stack
        1. `while(prev!=null || !stack.isEmpty())`, keep pushing node's left to the stack
        1. Update the node by pop up the stack, and record its value
        1. Make node point to node.right

**Pre-order and post-order are very similar** [Code](https://github.com/jiguan/LeetCode/blob/master/src/com/leetcode/util/Tree.java)

| Pre-order        | Post-order      |
| ---------------- | --------------- |
| Push right first | Push left first |
| Add to result    | Reverse result  |

## Questions

### Tree traverse

To flatten a binary tree, using *recusive* function and return a `Pair` to record the head and tail

- [Flatten Binary Tree to Linked List preorder](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/)
- Flatten Binary Tree to Linked List inorder

*InOrder* traverse is like a binary search, the __root__ value is in the middle. But we can know the __left subtree size__ and __right subtree size__. From *PreOrder* and *PostOrder* we can know the root value directly

- [Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- [Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)

### Lowest Common Ancestor

Find **ALL** ancestors of one node, then get the other's ancestor one by one and check if it has been visited already

- [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
- [Lowest Common Ancestor of Deepest Leaves](https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/)
- [Maximum Difference Between Node and Ancestor](https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/)
- [Karat] Ancestors


- [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)

### BST inorder traversal generates a sorted array
  1. [Minimum Absolute Difference in BST](https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/)

### Tree nodes/depth counting

  1. [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/) Try to solve it using recursive.
  1. [Find Bottom Left Tree Value](https://leetcode.com/problems/find-bottom-left-tree-value/description/)

### BFS and DFS

- [Average of Levels in Binary Tree](https://leetcode.com/problems/average-of-levels-in-binary-tree/description/)
- [Binary Tree Level Order Traversal II](https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/)

### Segment Tree

//https://www.youtube.com/watch?v=ZBHKZF5w4YU

