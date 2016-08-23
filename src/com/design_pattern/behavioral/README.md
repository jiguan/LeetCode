# Gang of Four
## Behavioral
The Template Method Pattern defines the skeleton of an algorithm in a method, deferring some steps to subclasses. Template method lets subclasses redefine certain steps of an algorithm without changing the algorithm's structure.

### Real world example
In the Arrays.sort method, it provides how to sort but subclasses need to provide details by implementing Comparable interface and compareTo method.