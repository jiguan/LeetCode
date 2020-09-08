package com.interview.java.basic;

class Outer_Demo {
    int num;

    // inner class
    public class Inner_Demo {
        public int getNum() {
            System.out.println("This is the getnum method of the inner class");
            return num;
        }
    }

    // instance method of the outer class
    void methodInnerClassCaller() {
        int num = 23;

        // method-local inner class
        class MethodInner_Demo {
            public void print() {
                System.out.println("This is method inner class " + num);
            }
        } // end of inner class

        // Accessing the inner class
        MethodInner_Demo inner = new MethodInner_Demo();
        inner.print();
    }

    // anonymous inner class
    void anonymousInnerClassCaller() {
        AnonymousInner inner = new AnonymousInner() {
            public void method() {
                System.out.println("Example of anonymous inner class");
            }
        };
        inner.method();
    }
}

abstract class AnonymousInner {
    public abstract void method();
}

public class InnerClassExample {

    public static void main(String args[]) {
        // Instantiating the outer class
        Outer_Demo outer = new Outer_Demo();
        Outer_Demo.Inner_Demo inner = outer.new Inner_Demo();
        inner.getNum();
    }
}