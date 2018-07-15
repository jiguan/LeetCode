package com.design.cards;

public class DeckOfCards {
    public static void main(String[] args) {
        Deck deck = new Deck();
        for(int i = 0 ; i < deck.getCapacity(); ++i) {
            System.out.print(deck.getNextCard() + " ");
        }
    }
}
