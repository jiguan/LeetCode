package com.design.cards;

public class BlackJackDeck extends Deck<BlackJackCard> {
    @Override
    protected void init() {
        for (int i = 0; i < getCapacity(); ++i) {
            for (SUITE suite : SUITE.values()) {
                cards.add(new BlackJackCard(i % 13 + 1, suite));
            }
        }
    }
}
