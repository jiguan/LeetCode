package com.design.cards;

public enum FACE {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(
            13);

    public final int value;
    
    FACE(int value) {
        this.value = value;
    }
    
    public String getLetter() {
        if (value >= 2 && value <= 10) return String.valueOf(value);
        else if (value == 11) return "J";
        else if (value == 12) return "Q";
        else if (value == 13) return "K";
        else return "A";
    }
    
    public static FACE getFace(int value) {
        for (FACE face : FACE.values()) {
            if (value == face.value) return face;
        }
        throw new IllegalArgumentException("Face not found");
    }
    
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}