#Tree
Here's a list of all questions related to the tree data structure. 

###Tree traversal
    ```
        3       Pre-order: 3 4 1 5 7
      4   7     Post-order: 1 5 4 7 3
    1  5        In-order: 1 4 5 3 7
    ```
 - Recursive solution is trivial. But it should be the first approach. 
 - Iteration [solution](https://github.com/jiguan/LeetCode/blob/master/src/com/leetcode/util/Tree.java) is tricky. 
  - Pre-order: Easy. 
        1. Push root to stack 
        2. Inside while loop, pop out the node, and save its value to result list 
        3. If node has left, push left to stack, so as right node  
  - Post-order: Tricky. Similar to pre-order
        1. Push the root to the stack 
        2. Pop up the stack, if node'e left is not null, add left to the stack, so as the right node. Record the node's val 
        3. **Outside the while loop, reverse res**
  - In-order: Tricky. 
        1. Create a node variable outside while loop and do not push node to the stack 
        2. `while(prev!=null || !stack.isEmpty())`, keep pushing node's left to the stack 
        3. Update the node by pop up the stack, and record its value 
        4. Make node point to node.right

    
