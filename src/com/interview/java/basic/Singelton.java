package com.interview.java.basic;

public class Singelton {


}


class EarlyLoad {
    private static EarlyLoad instance = new EarlyLoad();

    private EarlyLoad() {};

    public static EarlyLoad getInstance() {
        return instance;
    }
}


class LazyInit {
    private static LazyInit instance;

    private LazyInit() {};

    public static LazyInit getInstance() {
        if (instance == null) {
            instance = new LazyInit();
        }
        return instance;
    }
}


class DoubleCheck {
    // Use volatile to avoid partially constructed object situation: A half initialize it but B uses
    // it before A finish initialization
    private volatile DoubleCheck instance;

    private DoubleCheck() {

    }

    public DoubleCheck getInstance() {
        // Still need localRef since when we check null and return it, we don't need to get
        // access to the volatile variable instance anymore
        DoubleCheck localRef = instance;
        if (localRef == null) {
            // Obtain the lock.
            synchronized (this) {
                localRef = instance;
                // Double-check whether the variable has already been initialized: if another thread
                // acquired the lock first, it may have already done the initialization. If so,
                // return the initialized variable.
                if (localRef == null) {
                    instance = localRef = new DoubleCheck();
                }
            }
        }
        return localRef;
    }
}
