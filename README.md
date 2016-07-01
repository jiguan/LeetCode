# LeetCode

### Tricky questions
 - 214. Shortest Palindrome 
      - Based on the solution, I can see why doing so can result the correct answer, but the reason behind it is unclear. 
 - 123. Binary Tree Maximum Path Sum
      - The solution seems reasonable, but I cannot guarantee I can come up with a solution when encountering similar questions. 


### Should-be-able-to-solve questions
 - 97. Interleaving String
      -  A regular DP question. Each letters from S3 could be from S1 or S2 sequentially. Write a function to verify S3. 
 - 146. LRU Cache (Trivial)
      - The solution is not hard, but it needs extra attention. A good way to handle null pointer exception is by introducing fakeHead and fakeTail: fakeHead -> head -> ... -> tail -> fakeTail. Two helper functions make code concise: deleteNode, and moveToHead.
 - 138. Copy List with Random Pointer (Interesting， moderate) 
      - A straight forward solution is using a map to store (original node, new node). Since push and retrieve operation are time consuming, one optimization is append copied node behind the original node: node -> copy -> next node -> copy of next node
 - 295. Find Median from Data Stream (Data structure)
      - Basically this question is for PriorityQueue. Two priorityQueues. One is from small to large, another one is from large to small. When a new element is input, push it to large queue, and push it back to small queue. If small queue's size is larger than large queue, push the largest element back to large queue. The idea is make these two queues has almost same size, therefore when we need median, just pop up the smallest one in large queue and biggest one from small queue. 
 - 97.  Interleaving String (DP, must-practise) 
      - Determine the target string is made of two candidate strings. Note the order make be reserved. The key point is, a letter we don't know it is from s1 or s2. A dp array can solve the problem. 
 - 23.  Merge k Sorted Lists
      - A looks scary but not so hard question. Divide & conquer. I know how to merge two linked lists. How about k lists? Use partition to get mid LinkedList from (start, mid), (mid+1, end) and merge these two together. 
 - 297. Serialize and Deserialize Binary Tree
      - Not so hard question. Actually it should belong to medium level. Using queue and index is time consuming, try linked list instead. 
    