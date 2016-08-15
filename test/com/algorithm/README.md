#Data structures & Algorithm
##Data structures
#### Segment tree
Segment tree is used to calculate the sum/max/min value over a range of a mutable array
  1. The leaf nodes are values from the array
  2. Non-leaf nodes store their children nodes' range and sum
  3. Use binary approach to build, query and update data structure
  4. Time complexity
     - Space O(n), there are 2n-1 nodes
     - Build O(n)
     - Query O(logN)
     - Update O(logN)

#### Trie tree
Trie tree is used to dynamic sets of string. Common letter nodes share same trie nodes.
  1. Each trie node has a 26 length char array storing the next letter. An 'isEnd' flag indicates this node is the end (a word) or in the middle (bypass the word)
  
  
## Algorithm
#### [Heap sort](https://leetcode.com/problems/min-stack/)
#### [Topological sort](https://leetcode.com/problems/course-schedule-ii/)


#### Questions
  - [307. Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/)