package com.design_pattern.behavioral;

public abstract class CaffineBeverage {
	// template method
	final void prepareRecipe() {
		// in the template, each step of the algorithm is represented by a
		// method
		boilWater(); // some methods are handled by this class
		brew(); // some are handled by the subclass
		pourInCup();
		if (customerWantsCondiments()) {
			addCondiments();
		}
	}

	abstract void brew();

	abstract void addCondiments();

	void boilWater() {
		System.out.println("Boil water");
	}

	void pourInCup() {
		System.out.println("Pour into a cup");
	}

	// a hook method, may be overridden by its subclass
	boolean customerWantsCondiments() {
		//hook method can provide more functions, ex. print to screen
		return true;
	}
}
