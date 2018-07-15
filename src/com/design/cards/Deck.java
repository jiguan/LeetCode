package com.design.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private int capacity;
    private final static int DEFAULT_CARDS_NUM = 52;
    private List<Card> cards;

    public Deck() {
        this(DEFAULT_CARDS_NUM);
    }

    public Deck(int nums) {
        capacity = nums;
        cards = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; ++i) {
            for (SUITE suite : SUITE.values()) {
                cards.add(new Card(i, suite));
            }
        }
        shuffle(cards);
    }

    public Card getNextCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
    
    public int getCapacity() {
        return capacity;
    }

    public void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }
}
