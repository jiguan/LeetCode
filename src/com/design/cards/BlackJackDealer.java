package com.design.cards;

public class BlackJackDealer extends BlackJackPlayer {
    private boolean start = false;
    public BlackJackDealer(Deck<BlackJackCard> deck) {
        super(deck);
    }

    private static final int HIT_MIN = 16;

    // since there is a rule for min score
    public void play() {
        start = true;
        while (this.score < HIT_MIN) {
            getCard();
        }
    }

    @Override
    public String show() {
        if (start) {
            StringBuilder sb = new StringBuilder();
            for (BlackJackCard card : cards) {
                sb.append(card.getValue() + " ");
            }
            sb.append(String.format("Dealer's score is %d", score()));
            start = false;
            return sb.toString();
        } else {
            return cards.get(0).getValue() + " X ";
        }
    }
}
