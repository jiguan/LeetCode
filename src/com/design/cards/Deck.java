package com.design.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Deck<T extends Card> {
    private final int capacity;
    private final static int DEFAULT_CARDS_NUM = 52;
    protected List<T> cards;
    protected List<T> visited;

    public Deck() {
        this(DEFAULT_CARDS_NUM);
    }

    public Deck(int nums) {
        capacity = nums;
        cards = new ArrayList<>(nums);
        visited = new ArrayList<>(nums);
        // insert cards into the list
        init();
        shuffle();
    }

    protected abstract void init();
    
    public T dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        T card = cards.remove(0);
        visited.add(card);
        return card;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public int availableCardNum() {
        return cards.size();
    }
    
    public void shuffle() {
        cards.addAll(visited);
        visited.clear();
        Collections.shuffle(cards);
    }
}
