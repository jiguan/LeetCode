package practice.first.array;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import org.junit.Test;

public class ReconstructItinerary {
//	public List<String> findItinerary0(String[][] tickets) {
//		Map<String, PriorityQueue<String>> map = new HashMap<>();
//		for (String[] ticket : tickets) {
//			if (!map.containsKey(ticket[0])) {
//				map.put(ticket[0], new PriorityQueue<String>());
//			}
//			map.get(ticket[0]).offer(ticket[1]);
//		}
//		return find("JFK", map);
//	}
//
//	public List<String> find(String current, Map<String, PriorityQueue<String>> map) {
//		List<String> result = new ArrayList<>();
//		if(map.containsKey(current) && !map.get(current).isEmpty()) {
//			String next = map.get(current).peek();
//			result = find(next, map);
//		} else {
//			result.add(map.get(current).poll());
//		}
//		return result;
//	}
//	
	public List<String> findItinerary0(String[][] tickets) {
		Map<String, List<String>> map = new HashMap<>();
		for(String[] ticket : tickets) {
			List<String> possibleCities = map.get(ticket[0]);
			if(possibleCities == null) {
				possibleCities = new ArrayList<>();
				map.put(ticket[0], possibleCities);
			}
			possibleCities.add(ticket[1]);
		}
		return find("JFK", map, tickets.length);
	}
	
	static List<String> find(String current, Map<String, List<String>> map, int ticketNum) {
		List<String> result = new ArrayList<>();
		if(ticketNum==0) {
			result.add(current);
			return result;
		}
		List<String> possibleCities = map.get(current);
		if(possibleCities!=null) {
			int size = possibleCities.size();
			for(int i=0;i<size;i++) {
				if (result.size() != 0 &&i>1) {
					break;
				}
				String possibleCity = possibleCities.get(i);
				int possibleCityIndex = possibleCities.indexOf(possibleCity);
				possibleCities.remove(possibleCityIndex);
				List<String> possiblePath = find(possibleCity, map, ticketNum-1);
				if(possiblePath.size() == ticketNum) {
					possiblePath.add(0, current);
					result = result.size()==0 || (possiblePath.hashCode() < result.hashCode()) ? possiblePath : result;
				}
				possibleCities.add(possibleCityIndex, possibleCity);
			}
		}
		return result;
	}
	
	 public List<String> findItinerary(String[][] tickets) {
	        List<String> ret = new ArrayList<String>();
	        Map<String, PriorityQueue<String>> map = new HashMap<>();
	        for(String[] ticket : tickets) {
	            if(!map.containsKey(ticket[0])) {
	                map.put(ticket[0], new PriorityQueue<String>());
	            }
	            map.get(ticket[0]).offer(ticket[1]);;
	        }
	        Stack<String> stack = new Stack<String>();
	        stack.push("JFK");
	        while(!stack.isEmpty()) {
	            String next = stack.peek();
	            if(map.containsKey(next) && !map.get(next).isEmpty()) {
	                stack.push(map.get(next).poll());
	            } else {
	                ret.add(next);
	                stack.pop();
	            }
	        }
	        Collections.reverse(ret);
	        return ret;
	    }

	@Test
	public void test0() {
		String[][] tickets = new String[][] { { "MUC", "LHR" }, { "JFK", "MUC" }, { "SFO", "SJC" }, { "LHR", "SFO" } };
		List<String> result = findItinerary0(tickets);
		List<String> expect = Arrays.asList(new String[] { "JFK", "MUC", "LHR", "SFO", "SJC" });
		assertEquals(expect, result);
	}

	@Test
	public void test1() {
		String[][] tickets = new String[][] { { "JFK", "SFO" }, { "JFK", "ATL" }, { "SFO", "ATL" }, { "ATL", "JFK" },
				{ "ATL", "SFO" } };
		List<String> result = findItinerary0(tickets);
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
		List<String> result = findItinerary0(tickets);
	}
	
	@Test
	public void test3() {
		String[][] tickets = new String[][] {{ "JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
		List<String> result = findItinerary0(tickets);
		List<String> expect = Arrays.asList(new String[] {"JFK","NRT","JFK","KUL"} );
		assertEquals(expect, result);
	}

}
