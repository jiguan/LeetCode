package practice.first.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import practice.first.util.PrettyPrint;

//https://briangordon.github.io/2014/08/the-skyline-problem.html
public class TheSkylineProblem {
	public List<int[]> getSkyline(int[][] buildings) {
		if (buildings.length == 0)
			return new LinkedList<int[]>();
		return divide(buildings, 0, buildings.length - 1);
	}

	private LinkedList<int[]> divide(int[][] buildings, int start, int end) {
		if (start < end) {
			int mid = (end - start) / 2 + start;
			return merge(divide(buildings, start, mid), divide(buildings, mid + 1, end));
		} else { // start == end
			LinkedList<int[]> res = new LinkedList<int[]>();
			res.add(new int[] { buildings[start][0], buildings[start][2] });
			res.add(new int[] { buildings[start][1], 0 });
			return res;
		}
	}

	private LinkedList<int[]> merge(LinkedList<int[]> l1, LinkedList<int[]> l2) {
		LinkedList<int[]> res = new LinkedList<int[]>();
		 int h1 = 0, h2 = 0;
		while (!l1.isEmpty() && !l2.isEmpty()) {
			int x = 0, h = 0;
			if (l1.getFirst()[0] < l2.getFirst()[0]) {
				x = l1.getFirst()[0];
				h1 = l1.getFirst()[1];
				l1.removeFirst();
			} else if (l1.getFirst()[0] > l2.getFirst()[0]) {
				x = l2.getFirst()[0];
				h2 = l2.getFirst()[1];
				l2.removeFirst();
			} else {
				x = l1.getFirst()[0];
				h1 = l1.getFirst()[1];
				h2 = l2.getFirst()[1];
				l1.removeFirst();
				l2.removeFirst();
			}
			//The height of added strip is considered as maximum of current heights from skyline1 and skyline2.
			h = Math.max(h1, h2);
			//l1, l2 is pre-sorted
			//if h2 is newly set, h1 is the one on the left
			// h is set every time
			if (res.size() == 0 || h != res.getLast()[1]) {
				res.add(new int[] { x, h });
			}
		}
		res.addAll(l1);
		res.addAll(l2);
		return res;
	}

	@Test
	public void test0() {
		int[][] buildings = new int[][] { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
		List<int[]> res = getSkyline(buildings);
		// [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]
		for (int[] list : res) {
			PrettyPrint.print(list);
		}
	}

	@Test
	public void test1() {
		int[][] buildings = new int[][] { { 0, 2147483647, 2147483647 } };
		List<int[]> res = getSkyline(buildings);
		// [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]
		for (int[] list : res) {
			PrettyPrint.print(list);
		}
	}
}
