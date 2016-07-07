# LeetCode
## Hard questions
### Tricky questions
 - 214. Shortest Palindrome 
      - Based on the solution, I can see why doing so can result the correct answer, but the reason behind it is unclear. 
 - 123. Binary Tree Maximum Path Sum
      - The solution seems reasonable, but I cannot guarantee I can come up with a solution when encountering similar questions. 


### Should-be-able-to-solve questions
 - 97. Interleaving String
      -  A regular DP question. Each letters from S3 could be from S1 or S2 sequentially. Write a function to verify S3. 
 - 146. LRU Cache
      - The solution is not hard, but it needs extra attention. A good way to handle null pointer exception is by introducing fakeHead and fakeTail: fakeHead -> head -> ... -> tail -> fakeTail. Two helper functions make code concise: deleteNode, and moveToHead.
 - 138. Copy List with Random Pointer (Interesting， moderate) 
      - A straight forward solution is using a map to store (original node, new node). Since push and retrieve operation are time consuming, one optimization is append copied node behind the original node: node -> copy -> next node -> copy of next node
 - 295. Find Median from Data Stream (Data structure)
      - A PriorityQueue question. Two priorityQueues. One is from small to large, another one is from large to small. When a new element is input, push it to large queue, and push it back to small queue. If small queue's size is larger than large queue, push the largest element back to large queue. The idea is make these two queues has almost same size, therefore when we need median, just pop up the smallest one in large queue and biggest one from small queue. 
 - 97.  Interleaving String (DP, must-practise) 
      - Determine the target string is made of two candidate strings. Note the order make be reserved. The key point is, a letter we don't know it is from s1 or s2. A dp array can solve the problem. 
 - 23.  Merge k Sorted Lists
      - A looks scary but not so hard question. Divide & conquer. I know how to merge two linked lists. How about k lists? Use partition to get mid LinkedList from (start, mid), (mid+1, end) and merge these two together. 
 - 297. Serialize and Deserialize Binary Tree
      - Not so hard question. Actually it should belong to medium level. Using queue and index is time consuming, try linked list instead. 
 - 76.	Minimum Window Substring (two pointers)
      - A little bit tricky. The key point is how to find next candidate position. The idea is simple, two pointers, one is to find next position position, if found record it into start variable. But how to write the while loop, and the meanings of variables are trivial. **Need to pay attention**. 
 - 117. Populating Next Right Pointers in Each Node II
      - A little bit weird. It uses the same solution as Populating Next Right Pointers in Each Node I. A succinct solution is using a dummy fake head.
        1. Create a dummy fake head and make its next to root
        2. Create two variables: node and prev. prev = fakeHead and node = fakeHead.next 
        3. For each level, if node's left is not null. `prev.next = node.left` and `prev = prev.next`; Here, make fakeHead.next point to next level
      
   
  
## Medium questions
### Inorder, preorder and postorder
 - Recursive solution is trivial. But it should be the first approach. 
 - Iteration solution
      - Preorder: Easy. 
        1. Push root to stack 
        2. Inside while loop, pop out the node, and save its value to result list 
        3. If node has left, push left to stack, so as right node  
      - Inorder: Tricky. 
        1. Create a node variable outside while loop and do not push node to stack 
        2. `while(node!=null || !stack.isEmpty())`, keep pushing node's left to stack. 
        3. Update node by pop up the stack, and record its value 4. node points to node.right
      - Postorder: Tricky. Similar to Preorder. 
        1. Push root to stack 
        2. Pop up the stack, if node'e left is not null, add left to stack, so as right node. Record node's val 
        3. **Outside the while loop, reverse res**
    