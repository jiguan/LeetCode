package com.interview.pinterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;

    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
};

public class CloneGraph {
    public UndirectedGraphNode cloneGraphBfs(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> candidates = new LinkedList<>();
        Map<Integer, UndirectedGraphNode> finished = new HashMap<>();
        if (node == null) {
            return null;
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        candidates.offer(node);
        finished.put(clone.label, clone);
        while (!candidates.isEmpty()) {
            UndirectedGraphNode temp = candidates.poll();
            UndirectedGraphNode correspond = finished.get(temp.label);
            for (UndirectedGraphNode neighbor : temp.neighbors) {
                if (!finished.containsKey(neighbor.label)) {
                    finished.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    candidates.offer(neighbor);
                }
                correspond.neighbors.add(finished.get(neighbor.label));
            }
        }
        return clone;
    }
    
    public UndirectedGraphNode cloneGraphDfs(UndirectedGraphNode node) {
        return dfs(node,  new HashMap<Integer, UndirectedGraphNode>());
    }
    
    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
        if (node == null)
            return null;

        if (map.containsKey(node.label)) {
            return map.get(node.label);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone);
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, map));
        }
        return clone;
    }
}
