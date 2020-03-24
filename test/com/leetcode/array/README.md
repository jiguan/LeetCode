# Arrays

## Keynote

Sorting an array: `Arrays.sort(arr, (i1, i2) -> Integer.compare(i1[0], i2[0]));`

 `PriorityQueue` is implemented using *Heap* Data Structures and *Heap* has `O(log(n))` time complexity to insert and delete element.

 [Heap](https://en.wikipedia.org/wiki/Binary_heap) is a tree whose parent is larger than its children. [Demo](https://www.growingwiththeweb.com/data-structures/binary-heap/overview/)

- Build: `O(n log(n))`, *n* times of insertation
- Insert: `O(log(n))` insert the element into the bottom and compare with its parent, which is called *up-heap* operation
- Delete: `O(log(n))`, delete will take the root node and place the last element on its location then compare with its children
- Search: `O(n)`
- Heapify: `O(k)`, *k* is the height of tree

## Questions

### Binary Search

- [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) Good question to determine the condition

  ```java
  while(low < high) {
    int mid = (high - low) / 2 + low;
    if(target > nums[mid]) {
      low = mid + 1;
    } else {
      high = mid;
    }
  }
  res[0] = low
  ```

  ```java
  while(low < high) {
    int mid = (high - low) / 2 + low + 1;
    if(target < nums[mid]) {
      high = mid - 1;
    } else {
      low = mid;
    }
  }
  res[1] = high
  ```

### K elements

#### PriorityQueue

  1. Create a tuple class which stores indexes and val and implements Comparable interface
  1. Inside main function, create a `PriorityQueue` and save the first column values(`matrix[0][j]`) into the queue
  1. Write a `while(k-->0 && !queue.isEmpty())` to find out kth smallest element
  1. Inside the `while` loop, get the tuple by `queue.poll` and if the tuple's `x` has next(`tuple.x < mat.length - 1`) then save next element into the queue. Since the matrix is sorted, we can get a bigger candidate

Time complexities:

- Find minimum: O(1)
- Delete minimum: O(log n)
- Insert: O(log n)

#### Binary Search

Use quick sort, find the mid and compare the size

- [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums)
- [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix)
- [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/description/) Use two while loops to cut the leaves
- [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)
- [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/)

#### Bucket sort

Consider to use this approach if there is *top K frequency* inside

- [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

### Intervals

- [Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Easy
- [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Medium

### Permutation

- [Permutations](https://leetcode.com/problems/permutations/)
- [Next Permutation](https://leetcode.com/problems/next-permutation/)

### Stack

- [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/), test corner cases
- [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
- [Trapping Rain Water](stack/TrappingRainWater.java), same idea with __Largest Rectangle in Histogram__
- [Largest Rectangle in Histogram](stack/LargestRectangleInHistogram.java), Hard
- 1130. Minimum Cost Tree From Leaf Values
- 907. Sum of Subarray Minimums
- 901. Online Stock Span
- 856. Score of Parentheses
- 503. Next Greater Element II
- 496. Next Greater Element I

### BFS/DFS
- 269. [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) - Hard
- 301. [Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/) - Hard, Must Do

### Sliding Window

https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem

### Max length of subarray

Record the index

- [Maximum Size Subarray Sum Equals k](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/)
- [Contiguous Array](https://leetcode.com/problems/contiguous-array/)

### Min length of subarray

Two pointers

- [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)
- [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/)

### Segment tree

- [Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/)

- [Longest Repeating Character Replacement](LongestRepeatingCharacterReplacement.java) Great question. At first it may seem like a DP question.
- [Sliding Window Maximum](SlidingWindowMaximum.java) The solution dealing with the real sliding window problem using dequeue.
- https://leetcode.com/problems/minimum-window-substring/
- https://leetcode.com/problems/longest-substring-without-repeating-characters/
- https://leetcode.com/problems/substring-with-concatenation-of-all-words/
- https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
- https://leetcode.com/problems/find-all-anagrams-in-a-string/