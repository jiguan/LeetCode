package com.leetcode.util;

import java.util.List;

public class NestedInteger {
	private Object object;
	
	public NestedInteger(Object object) {
		this.object = object;
	}
	
	public boolean isInteger() {
		return object instanceof Integer;
	}

	public Integer getInteger() {
		return (Integer) object;
	}

	public List<NestedInteger> getList() {
		return (List<NestedInteger>) object;
	}
}
