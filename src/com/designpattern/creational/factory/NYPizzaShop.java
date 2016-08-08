package com.designpattern.creational.factory;

public class NYPizzaShop extends PizzaShop {

	@Override
	public Pizza createPizza(String type) {
		if(type.equals("cheese")) {
			return new NYCheesePizza();
		} else if(type.equals("pepperoni")) {
			return new NYPepperoniPizza();
		} else {
			return null;
		}
	}

}
