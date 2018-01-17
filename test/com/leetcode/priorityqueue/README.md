### PriorityQueue
Priority queue can be used to solve kth smallest questions. A key feature is given input is sorted or can be sorted. 
  1. Create a tuple class which stores indexes and val and implements Comparable interface
  2. Inside main function, create a `PriorityQueue` and save the first column values(`matrix[0][j]`) into the queue
  3. Write a `while(k-->0 && !queue.isEmpty())` to find out kth smallest element
  4. Inside the `while` loop, get the tuple by `queue.poll` and if the tuple's `x` has next(`tuple.x < mat.length - 1`) then save next element into the queue. Since the matrix is sorted, we can get a bigger candidate 

Time complexities:
- Find minimum: жи(1)
- Delete minimum: жи(log n)
- Insert: O(log n)

Questions: 
  - 373. [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums)
  - 378. [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix)