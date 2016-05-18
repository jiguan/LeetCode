package practice.first.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import org.junit.Test;

public class ReconstructItinerary {
    public List<String> findItinerary(String[][] tickets) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for(String[] pair : tickets) {
            map.computeIfAbsent(pair[0], k -> new ArrayList<>()).add(pair[1]);
        }
        for(ArrayList<String> value : map.values()) {
            Collections.sort(value);
        }
        
        List<String> route = new LinkedList<>();
        String city = "JFK";
        route.add(city);
        find(city, map, route, 0, tickets.length);
        return route;
    }
    
    private boolean find(String city, Map<String, ArrayList<String>> map, List<String> route, int ticketNum, int total) {
        if(ticketNum==total) {
            return true;
        }
        if(!map.containsKey(city) || map.get(city).isEmpty()) {
            return false;
        }
        int size = map.get(city).size();
        for(int i=0;i<size;i++) {
            String next = map.get(city).remove(i);
            route.add(next);
            if(find(next, map, route, ticketNum+1, total)) {
                return true;
            }
            route.remove(route.size()-1);
            map.get(city).add(i,next);
        }
        return false;
    }
    
    public List<String> findItinerary0(String[][] tickets) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(String[] ticket : tickets) {
            if(!map.containsKey(ticket[0])) {
                map.put(ticket[0], new PriorityQueue<String>());
            }
            map.get(ticket[0]).add(ticket[1]);
        }
        Stack<String> stack = new Stack<>();
        List<String> route = new LinkedList<>();
        stack.add("JFK");
        while(!stack.isEmpty()) {
            while(map.containsKey(stack.peek())&&!map.get(stack.peek()).isEmpty()) {
                stack.add(map.get(stack.peek()).poll());
            }
            route.add(0, stack.pop());
        }
        return route;
    }

	@Test
	public void test0() {
		String[][] tickets = new String[][] { { "MUC", "LHR" }, { "JFK", "MUC" }, { "SFO", "SJC" }, { "LHR", "SFO" } };
		List<String> result = findItinerary(tickets);
		List<String> expect = Arrays.asList(new String[] { "JFK", "MUC", "LHR", "SFO", "SJC" });
		assertEquals(expect, result);
	}

	@Test
	public void test1() {
		String[][] tickets = new String[][] { { "JFK", "SFO" }, { "JFK", "ATL" }, { "SFO", "ATL" }, { "ATL", "JFK" },
				{ "ATL", "SFO" } };
		List<String> result = findItinerary(tickets);
		List<String> expect = Arrays.asList(new String[] { "JFK", "ATL", "JFK", "SFO", "ATL", "SFO" });
		assertEquals(expect, result);
	}

	@Test
	public void test2() {
		String[][] tickets = new String[][] { { "AXA", "EZE" }, { "EZE", "AUA" }, { "ADL", "JFK" }, { "ADL", "TIA" },
				{ "AUA", "AXA" }, { "EZE", "TIA" }, { "EZE", "TIA" }, { "AXA", "EZE" }, { "EZE", "ADL" },
				{ "ANU", "EZE" }, { "TIA", "EZE" }, { "JFK", "ADL" }, { "AUA", "JFK" }, { "JFK", "EZE" },
				{ "EZE", "ANU" }, { "ADL", "AUA" }, { "ANU", "AXA" }, { "AXA", "ADL" }, { "AUA", "JFK" },
				{ "EZE", "ADL" }, { "ANU", "TIA" }, { "AUA", "JFK" }, { "TIA", "JFK" }, { "EZE", "AUA" },
				{ "AXA", "EZE" }, { "AUA", "ANU" }, { "ADL", "AXA" }, { "EZE", "ADL" }, { "AUA", "ANU" },
				{ "AXA", "EZE" }, { "TIA", "AUA" }, { "AXA", "EZE" }, { "AUA", "SYD" }, { "ADL", "JFK" },
				{ "EZE", "AUA" }, { "ADL", "ANU" }, { "AUA", "TIA" }, { "ADL", "EZE" }, { "TIA", "JFK" },
				{ "AXA", "ANU" }, { "JFK", "AXA" }, { "JFK", "ADL" }, { "ADL", "EZE" }, { "AXA", "TIA" },
				{ "JFK", "AUA" }, { "ADL", "EZE" }, { "JFK", "ADL" }, { "ADL", "AXA" }, { "TIA", "AUA" },
				{ "AXA", "JFK" }, { "ADL", "AUA" }, { "TIA", "JFK" }, { "JFK", "ADL" }, { "JFK", "ADL" },
				{ "ANU", "AXA" }, { "TIA", "AXA" }, { "EZE", "JFK" }, { "EZE", "AXA" }, { "ADL", "TIA" },
				{ "JFK", "AUA" }, { "TIA", "EZE" }, { "EZE", "ADL" }, { "JFK", "ANU" }, { "TIA", "AUA" },
				{ "EZE", "ADL" }, { "ADL", "JFK" }, { "ANU", "AXA" }, { "AUA", "AXA" }, { "ANU", "EZE" },
				{ "ADL", "AXA" }, { "ANU", "AXA" }, { "TIA", "ADL" }, { "JFK", "ADL" }, { "JFK", "TIA" },
				{ "AUA", "ADL" }, { "AUA", "TIA" }, { "TIA", "JFK" }, { "EZE", "JFK" }, { "AUA", "ADL" },
				{ "ADL", "AUA" }, { "EZE", "ANU" }, { "ADL", "ANU" }, { "AUA", "AXA" }, { "AXA", "TIA" },
				{ "AXA", "TIA" }, { "ADL", "AXA" }, { "EZE", "AXA" }, { "AXA", "JFK" }, { "JFK", "AUA" },
				{ "ANU", "ADL" }, { "AXA", "TIA" }, { "ANU", "AUA" }, { "JFK", "EZE" }, { "AXA", "ADL" },
				{ "TIA", "EZE" }, { "JFK", "AXA" }, { "AXA", "ADL" }, { "EZE", "AUA" }, { "AXA", "ANU" },
				{ "ADL", "EZE" }, { "AUA", "EZE" } };
		List<String> result = findItinerary(tickets);
	}
	
	@Test
	public void test3() {
		String[][] tickets = new String[][] {{ "JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
		List<String> result = findItinerary(tickets);
		List<String> expect = Arrays.asList(new String[] {"JFK","NRT","JFK","KUL"} );
		assertEquals(expect, result);
	}

}
