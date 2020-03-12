# Karat

1. Production Analysis, 2. Front-end Web application, 3. System£¬4. OOP, 5. testing

## OOP
### Abstraction
Abstraction is selecting data from a larger pool to show only the relevant details to the object. It helps to reduce programming complexity and effort. In Java, abstraction is accomplished using Abstract classes and interfaces.
 
### Composition vs Inheritance
Inheritance and Composition provides code reusablility, main difference between Composition and Inheritance in Java is that Composition allows reuse of code without extending it but for Inheritance you must extend the class for any reuse of code or functionality.
 
When you use Inheritance, you have to define which class you are extending in code, it cannot be changed at runtime, but with Composition you just define a Type which you want to use, which can hold its different implementation.
 
with Inheritance you can only extend one class, which means you code can only reuse just one class, not more than one. If you want to leverage functionalities from multiple class, you must use Composition.
 
Though both Inheritance and Composition allows code reuse, Inheritance breaks encapsulation because in case of Inheritance, sub class is dependent upon super class behavior.
 
### Dependency Injection
A technique whereby one object supplies the dependencies of another object. A dependency is an object that can be used (a service). An injection is the passing of a dependency to a dependent object (a client) that would use it.

### Document for a new class under Collection
- Description
- Thread safety
- All methods and the time it costs
- If null allowed
- Parameter
- Interfaces it implements
- All constructors
- All exceptions it could throw, if it is Iterable fail-safe vs fail-fast
- Any anonymous Inner Classes


### Testing

- What is mock