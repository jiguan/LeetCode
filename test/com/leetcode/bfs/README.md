# BFS/DFS

## Two ends approaching

If the question is asking minimal steps converting from `a` to `b`, we have to calculate all possible outcomes. Rather than iterating on a bigger set each time, we could have __2__ sets from both ends to minimize the times of iteration.

- [Open the Lock](https://leetcode.com/problems/open-the-lock/)

## Bottom up

If the question provides the bottom-up info, e.g. `node -> parent`, rather than converting to `parent -> [nodes]`, we could just use bottom up to traverse the tree.

- [Time Needed to Inform All Employees](https://leetcode.com/problems/time-needed-to-inform-all-employees)

## Questions

### Traditional DFS

- [Optimal Account Balancing](https://leetcode.com/problems/optimal-account-balancing/) - hard, how to convert it into dfs is not intuitive.

### Shortest distance

- [Shortest Distance from All Buildings](https://leetcode.com/problems/shortest-distance-from-all-buildings/) - hard
- [Network Delay Time](https://leetcode.com/problems/network-delay-time/) - hard
