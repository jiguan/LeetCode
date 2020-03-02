package com.leetcode.array;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import org.junit.Test;

public class ReconstructItinerary {
    List<String> tmp = new LinkedList<String>();
    public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] pair : tickets) {
            map.computeIfAbsent(pair[0], v -> new PriorityQueue<>()).add(pair[1]);
        }

        List<String> route = new LinkedList<>();
        String city = "JFK";
        dfs(map, city, route);
        return route;
    }

    private void dfs(Map<String, PriorityQueue<String>> map, String city, List<String> route) {
        route.add(city);
        PriorityQueue<String> arrives = map.get(city);
        while (arrives != null && !arrives.isEmpty()) {
            dfs(map, arrives.poll(), route);
        }
        // Add the leaf, last one
        tmp.add(0, city);
    }

    public List<String> findItinerary0(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (String[] ticket : tickets) {
            if (!map.containsKey(ticket[0])) {
                map.put(ticket[0], new PriorityQueue<String>());
            }
            map.get(ticket[0]).add(ticket[1]);
        }
        Stack<String> stack = new Stack<>();
        List<String> route = new LinkedList<>();
        stack.add("JFK");
        while (!stack.isEmpty()) {
            // If current city is not the final destination, keep searching
            while (map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()) {
                stack.add(map.get(stack.peek()).poll());
            }
            route.add(0, stack.pop());
        }
        return route;
    }

    @Test
    public void test0() {
        String[][] tickets =
                new String[][] {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};
        List<String> result = findItinerary0(tickets);
        List<String> expect = Arrays.asList(new String[] {"JFK", "MUC", "LHR", "SFO", "SJC"});
        assertEquals(expect, result);
    }

    @Test
    public void test1() {
        String[][] tickets = new String[][] {{"JFK", "SFO"}, {"JFK", "ATL"}, {"SFO", "ATL"},
                {"ATL", "JFK"}, {"ATL", "SFO"}};
        List<String> result = findItinerary(tickets);
        List<String> expect =
                Arrays.asList(new String[] {"JFK", "ATL", "JFK", "SFO", "ATL", "SFO"});
        assertEquals(expect, result);
    }

    @Test
    public void test2() {
        String[][] tickets = new String[][] {{"AXA", "EZE"}, {"EZE", "AUA"}, {"ADL", "JFK"},
                {"ADL", "TIA"}, {"AUA", "AXA"}, {"EZE", "TIA"}, {"EZE", "TIA"}, {"AXA", "EZE"},
                {"EZE", "ADL"}, {"ANU", "EZE"}, {"TIA", "EZE"}, {"JFK", "ADL"}, {"AUA", "JFK"},
                {"JFK", "EZE"}, {"EZE", "ANU"}, {"ADL", "AUA"}, {"ANU", "AXA"}, {"AXA", "ADL"},
                {"AUA", "JFK"}, {"EZE", "ADL"}, {"ANU", "TIA"}, {"AUA", "JFK"}, {"TIA", "JFK"},
                {"EZE", "AUA"}, {"AXA", "EZE"}, {"AUA", "ANU"}, {"ADL", "AXA"}, {"EZE", "ADL"},
                {"AUA", "ANU"}, {"AXA", "EZE"}, {"TIA", "AUA"}, {"AXA", "EZE"}, {"AUA", "SYD"},
                {"ADL", "JFK"}, {"EZE", "AUA"}, {"ADL", "ANU"}, {"AUA", "TIA"}, {"ADL", "EZE"},
                {"TIA", "JFK"}, {"AXA", "ANU"}, {"JFK", "AXA"}, {"JFK", "ADL"}, {"ADL", "EZE"},
                {"AXA", "TIA"}, {"JFK", "AUA"}, {"ADL", "EZE"}, {"JFK", "ADL"}, {"ADL", "AXA"},
                {"TIA", "AUA"}, {"AXA", "JFK"}, {"ADL", "AUA"}, {"TIA", "JFK"}, {"JFK", "ADL"},
                {"JFK", "ADL"}, {"ANU", "AXA"}, {"TIA", "AXA"}, {"EZE", "JFK"}, {"EZE", "AXA"},
                {"ADL", "TIA"}, {"JFK", "AUA"}, {"TIA", "EZE"}, {"EZE", "ADL"}, {"JFK", "ANU"},
                {"TIA", "AUA"}, {"EZE", "ADL"}, {"ADL", "JFK"}, {"ANU", "AXA"}, {"AUA", "AXA"},
                {"ANU", "EZE"}, {"ADL", "AXA"}, {"ANU", "AXA"}, {"TIA", "ADL"}, {"JFK", "ADL"},
                {"JFK", "TIA"}, {"AUA", "ADL"}, {"AUA", "TIA"}, {"TIA", "JFK"}, {"EZE", "JFK"},
                {"AUA", "ADL"}, {"ADL", "AUA"}, {"EZE", "ANU"}, {"ADL", "ANU"}, {"AUA", "AXA"},
                {"AXA", "TIA"}, {"AXA", "TIA"}, {"ADL", "AXA"}, {"EZE", "AXA"}, {"AXA", "JFK"},
                {"JFK", "AUA"}, {"ANU", "ADL"}, {"AXA", "TIA"}, {"ANU", "AUA"}, {"JFK", "EZE"},
                {"AXA", "ADL"}, {"TIA", "EZE"}, {"JFK", "AXA"}, {"AXA", "ADL"}, {"EZE", "AUA"},
                {"AXA", "ANU"}, {"ADL", "EZE"}, {"AUA", "EZE"}};
        List<String> result = findItinerary(tickets);
    }

    @Test
    public void test3() {
        String[][] tickets = new String[][] {{"JFK", "KUL"}, {"JFK", "NRT"}, {"NRT", "JFK"}};
        List<String> result = findItinerary(tickets);
        List<String> expect = Arrays.asList(new String[] {"JFK", "NRT", "JFK", "KUL"});
        assertEquals(expect, result);
    }
}
