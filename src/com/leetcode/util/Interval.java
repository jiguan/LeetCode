package com.leetcode.util;

public class Interval {
	public int start, end;

	public Interval() {
		start = 0;
		end = 0;
	}

	public Interval(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + start;
		result = prime * result + end;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof Interval == false) {
			return false;
		}
		Interval another = (Interval) obj;
		return another.start == this.start && another.end == this.end;
	}

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}
