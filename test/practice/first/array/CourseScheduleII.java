package practice.first.array;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] res = new int[numCourses];
		if(numCourses==0||prerequisites==null||prerequisites.length==0) return res ;
		Map<Integer, List<Integer>> map = new HashMap<>();
		for(int[] pair : prerequisites) {
			map.computeIfAbsent(pair[1], k -> new ArrayList<>()).add(pair[0]);
		}
		Set<Integer> visited = new HashSet<>();
		Stack<Integer> path = new Stack<>();
		for(java.util.Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			if(!visited.contains(entry.getKey())) {
				find(entry.getKey(), map, visited, path);
			}
		}
	
		if(path.size()==numCourses) {
			int i = 0;
			while(!path.isEmpty()) {
				res[i++] = path.pop();
			}
		}
		return res;
	}
	
	private void find(int preCourse, Map<Integer, List<Integer>> map, Set<Integer> visited, Stack<Integer> path) {
		if(visited.contains(preCourse)) {
			return;
		}
		visited.add(preCourse);
		if(map.containsKey(preCourse)) {
			for(Integer c : map.get(preCourse)) {
				find(c, map, visited, path);
			}
		}
		path.push(preCourse);
	}
	
	@Test
	public void test0() {
		int numCourses = 2;
		int[][]prerequisites =new int[][]{{1,0}};
		assertArrayEquals(new int[]{0,1}, findOrder(numCourses, prerequisites));
	}
	
	@Test
	public void test1() {
		int numCourses = 4;
		int[][]prerequisites =new int[][]{{1,0},{2,0},{3,1},{3,2}};
		assertArrayEquals(new int[]{0,2,1,3}, findOrder(numCourses, prerequisites));
	}
}
