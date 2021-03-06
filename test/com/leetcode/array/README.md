# Arrays

## List to array

- String array: `list.toArray(new String[list.size])`
- Integer array: `list.toArray(new Integer[list.size])`

## Sorting

- Array: `Arrays.sort(arr, (i1, i2) -> Integer.compare(i1[0], i2[0]));`
- PriorityQueue: `PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1])`

## Priority Queue

`PriorityQueue` is implemented using *Heap* Data Structures and *Heap* has `O(log(n))` time complexity to insert and delete element.

[Heap](https://en.wikipedia.org/wiki/Binary_heap) is a tree whose parent is larger than its children. [Demo](https://www.growingwiththeweb.com/data-structures/binary-heap/overview/)

- Build: `O(n log(n))`, *n* times of insertion
- Insert: `O(log(n))` insert the element into the bottom and compare with its parent, which is called *up-heap* operation
- Delete: `O(log(n))`, delete will take the root node and place the last element on its location then compare with its children
- Search: `O(n)`
- Heapify: `O(k)`, *k* is the height of tree

## Binary Search

```java
public int findFloorIndex(int[] arr, int target) {
    int start = 0, end = arr.length - 1;
    while (start < end) {
        int mid = (end - start + 1) / 2 + start;
        if (arr[mid] > target) {
            end = mid - 1;
        } else if (arr[mid] < target) {
            start = mid;
        } else {
            return mid;
        }
    }
    return start;
}
```

```java
public int findCeilIndex(int[] arr, int target) {
    int start = 0, end = arr.length - 1;
    while (start < end) {
        int mid = (end - start) / 2 + start;
        if (arr[mid] > target) {
            end = mid;
        } else if (arr[mid] < target) {
            start = mid + 1;
        } else {
            return mid;
        }
    }
    return start;
}
```

## Sum of array

If we need to record the sum till __CURRENT__ element, we need [n + 1], since `sum[j] - sum[i] = sum(i+1, i+2, .. j)`, we need a buffer for sum starting from 0: `sum(0, 2) = sum[2] - sum[-1]`.

- [Maximum Subarray](https://leetcode.com/problems/maximum-subarray/) - Must do
- [K-Concatenation Maximum Sum](https://leetcode.com/problems/k-concatenation-maximum-sum/) - No clue, could be follow up of the __Maximum Subarray__
- [Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/)
- [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

## Sliding window

### Stack approach

Some questions have same concept --- having a temporary container to store candidate. When a new element checking in, we do some operations to process our candidates. This conditioner could be a `Stack` or `Array`. Usually what it stores are indexes.

- [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/)

### Two pointers approach - works great for at most/minimum questions

Template:
```java
int findSubstring(String s){
    int [] map = new int[128];
    int counter; // check whether the substring is valid
    int begin=0, end=0; //two pointers, one point to tail and one  head
    int minLen; //the length of substring

    for() { /* initialize the hash map here */ }

    while(end< s.length()){

        if(map[s.charAt(end)] > 0){  /* modify counter here */ }

        while(/* counter condition */){ 
                
                /* update d here if finding minimum*/

            //increase begin to make it invalid/valid again
            
            if(map[s.charAt(start)] > 0){ /*modify counter here*/ }
        }  

        /* update d here if finding maximum*/
    }
    return minLen;
}
```

Minimum Window Substring

```java
public String minWindow(String s, String t) {
    int[] map = new int[128];
    
    for(char ch : t.toCharArray()) {
        map[ch]++;
    }
    
    int start = 0, end = 0;
    int minStart = 0, minLen = Integer.MAX_VALUE;
    int counter = t.length();
    while(end < s.length()) {
        char c1 = s.charAt(end);
        map[c1]--;
        end++;
        if(map[c1] >= 0) {
            counter--;
        }
        
        while(counter == 0) {
            if(minLen > end - start) {
                minLen = end - start;
                minStart = start;
            }
            char c2 = s.charAt(start);
            map[c2]++;
            start++;
            if(map[c2] > 0) {
                counter++;
            }
        }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
}
```

Longest Substring - at most K distinct characters

```java
public int lengthOfLongestSubstringKDistinct(String s, int k) {
    int[] map = new int[256];
    int start = 0, end = 0, maxLen = Integer.MIN_VALUE, counter = 0;

    while (end < s.length()) {
      final char c1 = s.charAt(end);
      if (map[c1] == 0) counter++;
      map[c1]++;
      end++;

      while (counter > k) {
        final char c2 = s.charAt(start);
        if (map[c2] == 1) counter--;
        map[c2]--;
        start++;
      }

      maxLen = Math.max(maxLen, end - start);
    }

    return maxLen;
}
```

## Questions

### Two pointers

- [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/)
- [Subarray Product Less Than K](https://leetcode.com/problems/subarray-product-less-than-k/)
- [Count Number of Teams](https://leetcode.com/contest/weekly-contest-182/problems/count-number-of-teams/) Contest question. Better than 3Sum
- [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) - Hard, but it is faster than using `Stack`

### Binary Search

- [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/) - Good question to determine the condition and return variable (return the one `+1`).

  ```java
  while (low < high) {
      int mid = (high - low) / 2 + low;
      if (target > nums[mid]) {
          low = mid + 1;
      } else {
          high = mid;
      }
  }
  res[0] = low
  ```

  ```java
  while (low < high) {
    int mid = (high - low) / 2 + low + 1;
    if (target < nums[mid]) {
        high = mid - 1;
    } else {
        low = mid;
    }
  }
  res[1] = high
  ```

- [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum/) - Hard, binary search against sum to find out the max sum of subarray
- [Divide Chocolate](https://leetcode.com/problems/divide-chocolate/) - Hard, but similar to __Split Array Largest Sum__
- [Missing Element in Sorted Array](MissingElementInSortedArray.java) - Google, a very good question. If either `left = mid` or `right = mid`, the ending condition need to take care of it

    ```java
    while (left < right - 1) {
        int mid = left + (right - left) / 2;
        missing = nums[mid] - nums[left] - (mid - left);

        if (k > missing) {
            k -= missing;
            left = mid;
        } else {
            right = mid;
        }
    }
    ```

### K elements

#### Using PriorityQueue

  1. Create a tuple class which stores indexes and val and implements Comparable interface
  1. Inside main function, create a `PriorityQueue` and save the first column values(`matrix[0][j]`) into the queue
  1. Write a `while(k-->0 && !queue.isEmpty())` to find out kth smallest element
  1. Inside the `while` loop, get the tuple by `queue.poll` and if the tuple's `x` has next(`tuple.x < mat.length - 1`) then save next element into the queue. Since the matrix is sorted, we can get a bigger candidate

Time complexities:

- Find minimum: O(1)
- Delete minimum: O(log n)
- Insert: O(log n)

Questions:

- [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) Good question to get familiar with custom _Comparator_.
- [Exam Room](https://leetcode.com/problems/exam-room/)

#### Using Quick Sort

Find the mid and compare the size.

Time complexities: __O(n ^ 2)__

Question:

- [Find K Pairs with Smallest Sums](https://leetcode.com/problems/find-k-pairs-with-smallest-sums)
- [Kth Smallest Element in a Sorted Matrix](https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix)
- [Reconstruct Itinerary](https://leetcode.com/problems/reconstruct-itinerary/description/) Use two while loops to cut the leaves
- [K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)
- [Search in Rotated Sorted Array II](https://leetcode.com/problems/search-in-rotated-sorted-array-ii/)

#### Using Bucket sort

Consider to use this approach if there is *top K frequency* inside

- [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)

### Intervals

- [Merge Intervals](https://leetcode.com/problems/merge-intervals/) - Easy
- [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii/) - Medium
- [Maximum Number of Events That Can Be Attended](https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/) - Medium, count how many max events we could attend. Sort the array by start date, and then poll the event with earliest end date
- [Meeting room](../../interview/google/MeetingRoom.java) - Medium, check on day `i` how many rooms are used

### Permutation

- [Permutations](https://leetcode.com/problems/permutations/)
- [Next Permutation](https://leetcode.com/problems/next-permutation/)

### Stack

- [Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences) - Very interesting question, no clue at first
- [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/) Good question to start with using _Stack_
- [Next Greater Node In Linked List](https://leetcode.com/problems/next-greater-node-in-linked-list/) Good question, similar to the [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/), but need some changes of mind - Is a `Map` necessary to store every element's next larger element in the __same__ array?
- [Asteroid Collision](https://leetcode.com/problems/asteroid-collision/), test corner cases
- [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
- [Remove Duplicate Letters](https://leetcode.com/problems/remove-duplicate-letters/) - Medium to Hard, good to learn to how to use a stack to store temporary result 
- [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) - Hard, same idea with __Largest Rectangle in Histogram__
- [Largest Rectangle in Histogram](stack/LargestRectangleInHistogram.java) - Harder than _Trapping Rain Water_

### Subarray

To check sum of a subarray, we could use a hash table to store sum of previous values

- [Shortest Subarray with Sum at Least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/) - hard

### BFS/DFS

- [Longest Increasing Path in a Matrix](https://leetcode.com/problems/longest-increasing-path-in-a-matrix/) - warm up, need to use cache
- [Remove Invalid Parentheses](https://leetcode.com/problems/remove-invalid-parentheses/) - hard, must-do

### Merge sort

- [Count of Smaller Numbers After Self](https://leetcode.com/problems/count-of-smaller-numbers-after-self/) - hard

### Sliding Window

- [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum/) - easy, warm up
- [Longest Substring with At Most K Distinct Characters](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/) - Even though it is marked as hard, but you can do it!
- [Minimum Window Subsequence](https://leetcode.com/problems/minimum-window-subsequence/) - Hard! The solution is very elegant. Worth reading!

https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem

### Max length of subarray

Record the index

- [Maximum Size Subarray Sum Equals k](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/)
- [Contiguous Array](https://leetcode.com/problems/contiguous-array/)

### Min length of subarray

### Segment tree

- [Range Sum Query - Mutable](https://leetcode.com/problems/range-sum-query-mutable/)
- [Longest Repeating Character Replacement](LongestRepeatingCharacterReplacement.java) Great question. At first it may seem like a DP question.
- [Sliding Window Maximum](SlidingWindowMaximum.java) The solution dealing with the real sliding window problem using dequeue.
- https://leetcode.com/problems/minimum-window-substring/
- https://leetcode.com/problems/longest-substring-without-repeating-characters/
- https://leetcode.com/problems/substring-with-concatenation-of-all-words/
- https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
- https://leetcode.com/problems/find-all-anagrams-in-a-string/

### Pattern
- [Detect Pattern of Length M Repeated K or More Times](https://leetcode.com/problems/detect-pattern-of-length-m-repeated-k-or-more-times/) - Easy, very interesting question. Seems pretty hard at first glance
