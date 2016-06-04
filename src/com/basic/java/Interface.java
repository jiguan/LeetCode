package com.basic.java;

public interface Interface {
    default void print() {
        System.out.println("Print from interface");
    }
}

/*
 * Abstract class can have constructor, where you need an object to call the methods in subclass.
 * But in case of default method without any reference you can invoke the interface method, like
 * InterfaceName.super.method() 
 */

class Implement1 implements Interface {

}


class Implement2 implements Interface {
    @Override
    public void print() {
        Interface.super.print();
    }
}
