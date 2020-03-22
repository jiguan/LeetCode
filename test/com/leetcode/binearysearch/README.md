# BFS/DFS

## Questions

- [Shortest Distance from All Buildings](https://leetcode.com/problems/shortest-distance-from-all-buildings/), hard

1. Flap the value to negative to make the building cell inaccessible
1. Check the accessible cell's value whether it equals previous building's id, if not then do not continue bfs from this cell

