# LeetCode

## General knowledge

- If the cases the limited,  __bucket sort__  is a good option
- Use this code to determine if the intervals have an overlap

```java
// find the overlap... if there is any...
int startMax = Math.max(a.start, b.start);
int endMin = Math.min(a.end, b.end);

if (startMax <= endMin) {
```

```java
// greatest common divisor
private int gcd(int a, int b) {
     while (b != 0) {
          int tmp = b;
          b = a % b;
          a = tmp;
     }
     return a;
}
```

## Coding questions

- [Array](test/com/leetcode/array/README.md)
- [Linked nodes](test/com/leetcode/linkednodes/README.md)
- [Tree](test/com/leetcode/tree/README.md)
- [Math](test/com/leetcode/math/README.md)
- [String](test/com/leetcode/string/README.md)
- [DP](test/com/leetcode/dp/README.md)
- [Greedy](test/com/leetcode/greedy/README.md)
- [Graph](test/com/leetcode/graph/README.md)
- [BFS/DFS](test/com/leetcode/bfs/README.md)
- [Bit](test/com/leetcode/bit/README.md)

### Frequent questions

- Linked Node: [LRU Cache](https://leetcode.com/problems/lru-cache/)
- [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/)
- Backtracking: [Permutations](https://leetcode.com/problems/permutations/), [Word Break](https://leetcode.com/problems/word-break/), [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)
- Priority Queue: [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)
- Pre order, in order and post order
- Sliding window
- [Counting islands](https://leetcode.com/problems/max-area-of-island/)
- Alien map
- Tree: [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)
- Partition: [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array)
- DP: [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/)

### Look back questions
- Minimum Cost to Cut a Stick

## [Java knowledge](src/com/interview/java/JavaInterviewQuestions.md)

## [System Design](src/com/interview/design/README.md)

- [URL Auto Complete](src/com/interview/design/autocomplete.md)
- [Distribute DB](src/com/interview/design/distributed_db.md)
- [Tiny URL](src/com/interview/design/tiny_url.md)
- [Mobile Pay](src/com/interview/design/mobile_pay.md)
- [Uber](src/com/interview/design/uber.md)

## [Behavior questions](src/com/interview/behavior/behavior.md)

## Algorithms

- [HeapSort](test/com/algorithm/HeapSort.java)
- [InsertSort](test/com/algorithm/InsertSort.java)
- [QuickSort](test/com/algorithm/QuickSort.java)
- [SegmentTree](test/com/algorithm/SegmentTreeRangeSum.java)
- [TrieTree](test/com/algorithm/TrieTree.java)
