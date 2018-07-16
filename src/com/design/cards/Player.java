package com.design.cards;

import java.util.ArrayList;
import java.util.List;

public class Player<T extends Card> {
    protected List<T> cards = new ArrayList<>();
    protected int score = 0;
    protected Deck<T> deck;
    
    public Player(Deck<T> deck) {
        this.deck = deck;
    }
    
    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void getCard() {
        T card = deck.dealCard();
        this.cards.add(card);
        this.score += card.getNum();
    }

    public int score() {
        return score;
    }
    
    public void reset() {
        cards.clear();
        score = 0;
    }
    
    public String show() {
        StringBuilder sb = new StringBuilder();
        for(T card : cards) {
            sb.append(card + " ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}