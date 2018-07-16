package com.design.cards;

public enum SUITE {
    // SPADE(0x2660), HEART(0x2661), DIAMOND(0x2662), CLUB(0x2663);
    SPADE("Spade"), HEART("Heart"), DIAMOND("Dimond"), CLUB("Club");

    private final String value;
    
    SUITE(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        // return Character.toString((char) value);
        return value;
    }
}