package practice.first.dp;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import practice.first.util.Point;

public class MaxPointsOnALine {
	public int maxPoints(Point[] points) {
		Map<Integer, Map<Integer, Map<Integer, Integer>>> mat = new HashMap<>(); 
		for(Point p : points) {
			if(mat.containsKey(p.x)) {
				Map<Integer, Map<Integer, Integer>> xMap = mat.get(p.x);
				if(xMap.containsKey(p.y)) {
					Map<Integer, Integer> yMap = xMap.get(p.y);
					if(yMap.containsKey(0)) {
					}
					
					
				}
			}
		}
		return 0;
		
	}
	
	
	
	
	
	@Test
	public void test0() {
		Point[] points = new Point[4];
		points[0] = new Point(1, 2);
		points[1] = new Point(2, 2);
		points[2] = new Point(3, 2);
		points[3] = new Point(4, 2);
		assertEquals(4, maxPoints(points));
	}

	@Test
	public void test2() {
		Point[] points = new Point[4];
		points[0] = new Point(2, 2);
		points[1] = new Point(2, 2);
		points[2] = new Point(2, 2);
		points[3] = new Point(2, 2);
		assertEquals(4, maxPoints(points));
	}

	@Test
	public void test1() {
		Point[] points = new Point[4];
		points[0] = new Point(1, 2);
		points[1] = new Point(1, 3);
		points[2] = new Point(1, 4);
		points[3] = new Point(1, 5);
		assertEquals(4, maxPoints(points));
	}

	@Test
	public void test3() {
		Point[] points = new Point[4];
		points[0] = new Point(2, 3);
		points[1] = new Point(2, 3);
		points[2] = new Point(2, 3);
		points[3] = new Point(2, 3);
		assertEquals(4, maxPoints(points));
	}

	@Test
	public void test4() {
		Point[] points = new Point[4];
		points[0] = new Point(2, 2);
		points[1] = new Point(1, 1);
		points[2] = new Point(0, 0);
		points[3] = new Point(2, 3);
		assertEquals(3, maxPoints(points));
	}

}
