package com.design.cards;

public enum SUITE {
    SPADE(0x2660), HEART(0x2661), DIAMOND(0x2662), CLUB(0x2663);

    private final int value;
    
    SUITE(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Character.toString((char) value);
    }
}