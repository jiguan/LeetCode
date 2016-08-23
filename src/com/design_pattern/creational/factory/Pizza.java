package com.design_pattern.creational.factory;

public abstract class Pizza {
	String name;
	String dough;
	String sauce;
	String toppings = "";

	public void prepare() {
		System.out.println("Adding dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings:"); 
		for(String topping : toppings.split(" ")) {
			System.out.println(topping+" ");
		}
	}

	public void bake() {
		System.out.println("Bake it at 350 degree for 25 mins");
	}

	public void cut() {
		System.out.println("Cut it into diagonal slices");
	}

	public void box() {
		System.out.println("Put it into a box");
	}

}
