# Arrays

## Keynote

Sorting an array: `Arrays.sort(arr, (i1, i2) -> Integer.compare(i1[0], i2[0]));`

 `PriorityQueue` is implemented using *Heap* Data Structures and *Heap* has `O(log(n))` time complexity to insert and delete element.

 [*Heap*](https://en.wikipedia.org/wiki/Binary_heap) is a tree whose parent is larger than its children. [Demo](https://www.growingwiththeweb.com/data-structures/binary-heap/overview/)

- Build: `O(n log(n))`, *n* times of insertation
- Insert: `O(log(n))` insert the element into the bottom and compare with its parent, which is called *up-heap* operation
- Delete: `O(log(n))`, delete will take the root node and place the last element on its location then compare with its children
- Search: `O(n)`
- Heapify: `O(k)`, *k* is the height of tree

## Questions
### Bucket sort
Think about if we could use *bucket sort*, even if there is *top K* inside!
- [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

### Intervals

- [Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Easy
- [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Medium

### Backtrack
- 46. [Permutations](https://leetcode.com/problems/permutations/) - Medium, Must Do

### Stack
- 1130. Minimum Cost Tree From Leaf Values
- 907. Sum of Subarray Minimums
- 901. Online Stock Span
- 856. Score of Parentheses
- 503. Next Greater Element II
- 496. Next Greater Element I
- 84. Largest Rectangle in Histogram
- 42. Trapping Rain Water

### BFS/DFS
- 269. [Alien Dictionary](https://leetcode.com/problems/alien-dictionary/) - Hard
- 301. [Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/) - Hard, Must Do

### First K elements
#### PriorityQueue

  1. Create a tuple class which stores indexes and val and implements Comparable interface
  2. Inside main function, create a `PriorityQueue` and save the first column values(`matrix[0][j]`) into the queue
  3. Write a `while(k-->0 && !queue.isEmpty())` to find out kth smallest element
  4. Inside the `while` loop, get the tuple by `queue.poll` and if the tuple's `x` has next(`tuple.x < mat.length - 1`) then save next element into the queue. Since the matrix is sorted, we can get a bigger candidate 

#### Questions: 
  - 373. [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums)
  - 378. [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix)
  - [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/description/) Use two while loops to cut the leaves
  - 973. [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)
  
### Binary Search
### Medium
  - 81. Search in Rotated Sorted Array II

### Hard
  - 378. Kth Smallest Element in a Sorted Matrix 