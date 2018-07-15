package com.design.cards;

public class Card {
    private int value;
    private SUITE suite;

    public Card(int value, SUITE suite) {
        this.value = value;
        this.suite = suite;
    }

    public int getValue() {
        return value;
    }

    public SUITE getSuite() {
        return suite;
    }

    @Override
    public String toString() {
        return "Card{" + "value=" + value + ", suite=" + suite.toString() + '}';
    }
}
