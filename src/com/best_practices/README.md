#Best practices

### The Principle of Lease Knowledge
We should only invoke methods that belongs to:
* The object itself
* Objects passed in as a parameter
* Any object that method creates
* Any components of the object

The principle reduces the dependencies between objects but also results in more "wrapper" classes to handle method calls to other components which may decrease runtime performance. 

```
public class Car {
	Engine engine;  //class component
    
    public void start(Key key) {
    	Doors doors = new Doors();   //method created object
        
    	boolean authorized = key.turns();  //method from passed in object
        if(authorized) {
        	engine.start();   //component's method
            updateDashboardDisplay(); //local method
            doors.lock();  //method from the object self-created
        }
        
        public void updateDashboardDisplay() {
        }
    }
}
```

### The Hollywood Principle
Low-level components can hook into a system, but High-level components determine when they are called. In the CaffineBeverage class, the high level is the abstract class since it controls the algorithm. 

#### Hollywoord Principle vs Dependency Inversion
The **Dependency Inversion Principle** teaches us to avoid the use of concrete classes and instead work as much as possible with abstractions. The **Hollywood Principle** is a technique for building frameworks or components so that lower-level components can be hooked into the computation, but without creating dependencies between the lower-level components and the higher-level layers. So, they both have the goal of decoupling, but the **Dependency Inversion** makes a much stronger and general statement about how to avoid dependencies in design. The **Hollywood Principle** gives us a technique for creating designs that allow low-level structures to interoperate while preventing other classes from becoming too dependent on them. 

