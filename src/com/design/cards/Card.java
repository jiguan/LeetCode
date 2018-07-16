package com.design.cards;

public class Card {
    protected FACE face;
    protected SUITE suite;

    public Card(FACE face, SUITE suite) {
        this.face = face;
        this.suite = suite;
    }

    public Card(int value, SUITE suite) {
        this(FACE.getFace(value), suite);
    }
    
    public String getValue() {
        return face.getLetter();
    }
    
    // subclass may have a different rule to calculate the value for certain cards
    public int getNum() {
        return face.value;
    }

    public SUITE getSuite() {
        return suite;
    }

    @Override
    public String toString() {
        return "Card{" + "value=" + face.value + ", suite=" + suite.toString() + '}';
    }
}
