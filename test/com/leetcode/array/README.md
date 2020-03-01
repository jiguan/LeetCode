## Arrays

### Tricks
- Sorting an array `Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));`

### Questions category
#### Intervals
- 56. [Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Easy
- 253. [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Medium, need some thoughts

#### Stack
- 1130. Minimum Cost Tree From Leaf Values
- 907. Sum of Subarray Minimums
- 901. Online Stock Span
- 856. Score of Parentheses
- 503. Next Greater Element II
- 496. Next Greater Element I
- 84. Largest Rectangle in Histogram
- 42. Trapping Rain Water


### PriorityQueue
Priority queue can be used to solve kth smallest questions. A key feature is given input is sorted or can be sorted. 
  1. Create a tuple class which stores indexes and val and implements Comparable interface
  2. Inside main function, create a `PriorityQueue` and save the first column values(`matrix[0][j]`) into the queue
  3. Write a `while(k-->0 && !queue.isEmpty())` to find out kth smallest element
  4. Inside the `while` loop, get the tuple by `queue.poll` and if the tuple's `x` has next(`tuple.x < mat.length - 1`) then save next element into the queue. Since the matrix is sorted, we can get a bigger candidate 

### Questions: 
  - 373. [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums)
  - 378. [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix)
  - [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/description/) Use two while loops to cut the leaves
  
### Binary Search
#### Medium
  - 81. Search in Rotated Sorted Array II

#### Hard
  - 378. Kth Smallest Element in a Sorted Matrix 