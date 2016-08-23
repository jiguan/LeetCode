package com.design_pattern.creational.factory;

public abstract class PizzaShop {

	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		
		return pizza;
	}
	
	//make createPizza into abstract so that each type of pizza has its only implementation
	protected abstract Pizza createPizza(String type);
}
