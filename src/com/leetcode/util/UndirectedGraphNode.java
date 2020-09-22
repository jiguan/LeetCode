package com.leetcode.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndirectedGraphNode {
    public int value;
    public List<UndirectedGraphNode> neighbors = new ArrayList<UndirectedGraphNode>();

    public UndirectedGraphNode(int x) {
        value = x;
    }
    
    public static UndirectedGraphNode build(String graph) {
        String[] nodes = graph.split("#");
        Map<String, UndirectedGraphNode> map = new HashMap<>();
        for(String circle : nodes) {
            String[] node = circle.split(",");
            UndirectedGraphNode main = get(node[0], map);
            for(int i=1;i<node.length;i++) {
                main.neighbors.add(get(node[i], map));
            }
        }
        return map.values().iterator().next();
    }
    
    private static UndirectedGraphNode get(String label, Map<String, UndirectedGraphNode> map) {
        if(map.containsKey(label)) return map.get(label);
        UndirectedGraphNode node = new UndirectedGraphNode(Integer.parseInt(label));
        map.put(label, node);
        return node;
    }
}
