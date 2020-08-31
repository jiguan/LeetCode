# LeetCode

## Categories

- [Array](test/com/leetcode/array/README.md)
- [Linked nodes](test/com/leetcode/linkednodes/README.md)
- [Tree](test/com/leetcode/tree/README.md)
- [Math](test/com/leetcode/math/README.md)
- [String](test/com/leetcode/string/README.md)
- [DP](test/com/leetcode/dp/README.md)
- [Greedy](test/com/leetcode/greedy/README.md)
- [Graph](test/com/leetcode/graph/README.md)
- [BFS/DFS](test/com/leetcode/bfs/README.md)

## Frequent questions

- [LRU Cache](https://leetcode.com/problems/lru-cache/)
- [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree/)

## No clue questions

- [Minimum Area Rectangle](https://leetcode.com/problems/minimum-area-rectangle/)

## Tips

Some questions have special tricks:

- [Valid Square](https://leetcode.com/problems/valid-square/)



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

## Must do to warm up before an interview
- [Maximal Rectangle](https://leetcode.com/problems/maximal-rectangle/) - Hard, tricky question

## Look back questions
- Minimum Cost to Cut a Stick