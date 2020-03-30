#Data structures & Algorithm
##Data structures
#### Segment tree
Segment tree is used to calculate the sum/max/min value over a range of a mutable array
  1. The leaf nodes are values from the array
  2. Non-leaf nodes store their children nodes' range and sum
  3. Use binary approach to build, query and update data structure
  4. Time complexity
     - Space O(n), there are `2n-1` nodes
     - Build O(n)
     - Query O(logN)
     - Update O(logN)

[Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/description/)

#### Trie tree
Trie tree is used to dynamic sets of string. Common letter nodes share same trie nodes.
  1. Each trie node has a 26 length char array storing the next letter. An 'isEnd' flag indicates this node is the end (a word) or in the middle (bypass the word)
  
  
## Algorithm and Concepts
### Heap
A binary heap is a nearly complete binary tree; that is, all levels of the tree, except possibly the last one (deepest) are fully filled, and, if the last level of the tree is not complete, the nodes of that level are filled from left to right.

### [Heap sort](https://leetcode.com/problems/min-stack/)
1. Call the heapify() function on the list. This builds a heap from a list in O(n) operations.
2. Swap the first element of the list with the final element. Decrease the considered range of the list by one.
3. Call the siftDown() function on the list to sift the new first element to its appropriate index in the heap.
5. Go to step (2) unless the considered range of the list is one element.

#### [Topological sort](https://leetcode.com/problems/course-schedule-ii/)


#### Questions
- [Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/)