### Factory pattern
| Abstract | Concrete |
| ------------- | ------------- |
| Pizza | NYCheesePizza, NYPepperoniPizza  |
| PizzaShop  | NYPizzaShop  |

The PizzaShop is a factory method. Depend on passed in parameter, different type of Pizza is made.

#### Abstract vs Interface
One thing should take notice, inside Pizza class, it defines related methods. And inside PizzaShop, the procedure is recorded. If there is any common method, than abstract should be used. Otherwise, interface is better.