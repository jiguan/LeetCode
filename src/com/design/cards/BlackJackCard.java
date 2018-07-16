package com.design.cards;

public class BlackJackCard extends Card {

    public BlackJackCard(int value, SUITE suite) {
        super(value, suite);
    }
    
    @Override
    public int getNum() {
        if (this.face == FACE.ACE) { // Ace
            return 11; 
        } else if (this.face == FACE.JACK || this.face == FACE.QUEEN || this.face == FACE.KING) { // Face card
            return 10;
        } else { // Number card
            return this.face.value;
        }
    }

}
