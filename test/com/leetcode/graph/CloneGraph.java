package com.leetcode.graph;

import java.util.HashMap;

import com.leetcode.util.UndirectedGraphNode;

public class CloneGraph {
    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();

    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        if(map.containsKey(node.value)) {
            return map.get(node.value);
        }
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.value);
        map.put(newNode.value, newNode);
        for (UndirectedGraphNode n : node.neighbors) {
            newNode.neighbors.add(cloneGraph(n));
        }
        return newNode;
    }
    
}
