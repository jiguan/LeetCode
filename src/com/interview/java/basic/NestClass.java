package com.interview.java.basic;
public class NestClass {
	
	static class StaticClass {
		public void print() {
			System.out.println("Static class");
		}
	}

	StaticClass staticClass = new StaticClass();
	
	class NormalClass {
		public void print() {
			System.out.println("Normal class");
		}
	}
	NormalClass normalClass = new NormalClass();
	
}

class TestClass {
	NestClass solution = new NestClass();
	NestClass.NormalClass normalClass = solution.new NormalClass();
}