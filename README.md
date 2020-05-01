# LeetCode

## Categories

- [Array](test/com/leetcode/array/README.md)
- [Linked Nodes](test/com/leetcode/linkednodes/README.md)
- [Tree](test/com/leetcode/tree/README.md)
- [Math](test/com/leetcode/math/README.md)
- [String](test/com/leetcode/string/README.md)
- [DP](test/com/leetcode/dp/README.md)
- [Graph](test/com/leetcode/graph/README.md)

## Must-do

- [LRU Cache](https://leetcode.com/problems/lru-cache/)
- [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/)
- [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)

Keynote: use a `Deque` to store __index__, everytime we encounter a larger element, starting from the **TAIL** dump all indexes whose values are **SMALLER** than current one. We have to store __index__, otherwise we cannot dump element kicked from the window

## General knowledge

- If the cases the limited,  __bucket sort__  is a good option
- Use this code to determine if the intervals have an overlap

```java
// find the overlap... if there is any...
int startMax = Math.max(a.start, b.start);
int endMin = Math.min(a.end, b.end);

if (startMax <= endMin ) {
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

## Algorithms

- [HeapSort](test/com/algorithm/HeapSort.java)
- [InsertSort](test/com/algorithm/InsertSort.java)
- [QuickSort](test/com/algorithm/QuickSort.java)
- [SegmentTree](test/com/algorithm/SegmentTreeRangeSum.java)
- [TrieTree](test/com/algorithm/TrieTree.java)
