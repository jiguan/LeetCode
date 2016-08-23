# Gang of Four
## Structural

### Adapter Pattern
The Adapter Pattern converts the interface into another.

#### Class (Inheritance) vs Object Adapter (Composition)
* The Class adapter uses multiple inheritance, cannot do it in Java. 
* The Object adapter implements the target interface. When it gets called, it turns around and delegates the calls to adaptee (old) method.

### Decorator Pattern
Add new behavior to the interface


### Facade Pattern
The Facade Pattern provides a unified interface to a set of interfaces in a subsystem. Facade defines a higher level interface that makes the subsystem easier to use. 

In another word, it provides a simplified interface for clients to subsystem's functions.

A good advantage is if subsystem code changed, the client code doesn't need to change, only facade.
