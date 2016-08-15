package com.algorithm;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import com.leetcode.util.Graph;
import com.leetcode.util.Vertex;

public class TopSort<T> {
	/*
	 * Goal: determine a path to visit all accessible nodes, while there are
	 * dependencies between nodes A->C, B->C, C->D, D->F, B->E, F->G. The
	 * solution is to use a visited set, and a result stack. Every time pick a
	 * node from the graph, add it into visited set. If it has child vertex,
	 * keep pushing it. If it doesn't have any child vertex unvisited, then add
	 * this node to the result stack so that we can make sure all children node
	 * are reached after this node is visited (since it is a stack).
	 * 
	 * Very similar to DFS.
	 */

	public Deque<Vertex<T>> topsort(Graph<T> graph) {
		Deque<Vertex<T>> stack = new ArrayDeque<>();
		Set<Vertex<T>> visited = new HashSet<>();

		for (Vertex<T> vertex : graph.getAllVertex()) {
			if (!visited.contains(vertex)) {
				sort(vertex, stack, visited);
			}
		}
		return stack;
	}

	private void sort(Vertex<T> vertex, Deque<Vertex<T>> stack, Set<Vertex<T>> visited) {
		visited.add(vertex);
		for (Vertex<T> child : vertex.getAdjacentVertexes()) {
			if (!visited.contains(child)) {
				sort(child, stack, visited);
			}
		}
		stack.offerFirst(vertex);
	}

	public static void main(String[] args) {

		Graph<Integer> graph = new Graph<>(true);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(2, 4);
		graph.addEdge(4, 5);
		graph.addEdge(3, 5);
		TopSort<Integer> topsort = new TopSort<>();
		Deque<Vertex<Integer>> res = topsort.topsort(graph);
		while (!res.isEmpty()) {
			System.out.print(res.pop() + "->");
		}
		System.out.println("null");
	}
}
