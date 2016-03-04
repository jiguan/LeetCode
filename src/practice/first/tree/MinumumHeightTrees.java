package practice.first.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MinumumHeightTrees {
	public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
		if(n==1) return Arrays.asList(0);
		
		Set<Integer> nodes = new HashSet<Integer>();
		int[] degree = new int[n];
		Map<Integer, Set<Integer>> access = new HashMap<Integer, Set<Integer>>();
		for(int i=0;i<edges.length;i++) {
			int[] edge = edges[i];
			int a = edge[0], b = edge[1];
			nodes.add(a); nodes.add(b);
			degree[a]++; degree[b]++;
			Set<Integer> aSet = access.get(a)==null ? new HashSet<Integer>() : access.get(a);
			Set<Integer> bSet = access.get(b)==null ? new HashSet<Integer>() : access.get(b);
			aSet.add(b);
			bSet.add(a);
			access.put(a, aSet);
			access.put(b, bSet);
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		while(n>2) {
			for(int i=0;i<degree.length;i++) {
				if(degree[i]==1) {
					queue.add(i);
				}
			}
			
			while(queue.size()>0) {
				int leaf = queue.poll();
				degree[leaf] = -1;
				Set<Integer> connected = access.get(leaf);
				for(int node : connected) {
					degree[node] -= 1;
				}
				n--;
			}
		}
		List<Integer> result = new ArrayList<Integer>();
		for(int i=0;i<degree.length;i++) {
			if(degree[i]>=0) {
				result.add(i);
			}
		}
		return result;
		
	}
	
	
	public static void main(String[] args) {
		int n = 6; 
		int[][] edges = new int[][] {{0,3}, {1,3}, {2,3},{4,3}, {5,4}};
		List<Integer> result = findMinHeightTrees(n, edges);
		for(int i : result) {
			System.out.print(i);
		}
		System.out.println();
		edges = new int[][] {{0,1}, {1,2}, {1,3}};
		n = 4;
		result = findMinHeightTrees(n, edges);
		for(int i : result) {
			System.out.print(i);
		}
		System.out.println();
		n = 1;
		edges = new int[][]{{}};
		result = findMinHeightTrees(n, edges);
		for(int i : result) {
			System.out.print(i);
		}
		System.out.println();
		n = 10;
		edges = new int[][]{{0,1},{0,2},{0,3},{2,4},{0,5},{5,6},{6,7},{2,8},{7,9}};
		result = findMinHeightTrees(n, edges);
		for(int i : result) {
			System.out.print(i);
		}
	}
	
}
