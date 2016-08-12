### Common data structures

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
     
#### Questions
  - [307. Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/)