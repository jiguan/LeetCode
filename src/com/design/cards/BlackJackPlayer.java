package com.design.cards;

import java.util.HashSet;
import java.util.Set;

public class BlackJackPlayer extends Player<BlackJackCard> {
    protected int initCardNum = 2;

    public BlackJackPlayer(Deck<BlackJackCard> deck) {
        super(deck);
        init();
    }
    
    protected void init() {
        for (int i = 0; i < initCardNum; ++i) {
            getCard();
        }
    }
    
    protected int aceNum = 0;

    public boolean isBusted() {
        return score() > 21;
    }

    @Override
    public void reset() {
        super.reset();
        aceNum = 0;
        init();
    }

    @Override
    public void getCard() {
        BlackJackCard card = deck.dealCard();
        if (card.face == FACE.ACE) {
            aceNum++;
        }
        this.cards.add(card);
        this.score += card.getNum();
    }

    @Override
    public int score() {
        while (this.score > 21 && aceNum > 0) {
            this.score -= 10;
            aceNum--;
        }
        return this.score;
    }

    public boolean isBlackJack() {
        Set<BlackJackCard> set = new HashSet<>();
        return score == 21 && set.size() == 2 && set.contains(FACE.ACE);
    }

    @Override
    public String show() {
        StringBuilder sb = new StringBuilder();
        for (BlackJackCard card : cards) {
            sb.append(card.getValue() + " ");
        }
        sb.append("Your score is: " + score());
        return sb.toString();
    }

}
