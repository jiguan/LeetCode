package com.design.cards;

import java.util.Scanner;

public class DeckOfCards {
    public static void main(String[] args) {
        Deck<BlackJackCard> deck = new BlackJackDeck();
        BlackJackDealer dealer = new BlackJackDealer(deck);
        BlackJackPlayer player = new BlackJackPlayer(deck);

        Scanner scan = new Scanner(System.in);
        boolean roundOver = false;
        boolean gameOver = false;

        while (!gameOver) {
            if (roundOver) {
                System.out.println("This round is over. Do you want to continue? (1) Yes (2) No");
                int option = scan.nextInt();
                if (option == 1) {
                    roundOver = false;
                    player.reset();
                    dealer.reset();
                } else {
                    System.out.println("Bye");
                    gameOver = true;
                }
                continue;
            }
            deck.shuffle();
            System.out.println("Dealer's cards: \n" + dealer.show());
            while (!roundOver) {
                System.out.println("Your cards: \n" + player.show());
                System.out.println("Do you want to continue? (1) Call (2) Pass");
                int option = scan.nextInt();
                switch (option) {
                    case 1 :
                        player.getCard();
                        if (player.isBusted()) {
                            System.out.println("You are busted. Dealer wins.");
                            System.out.println(player.show());
                            roundOver = true;
                        }
                        break;
                    case 2 :
                        dealer.play();
                        if (dealer.isBusted()) {
                            System.out.println("Dealer is busted. You win.");
                        } else {
                            if (dealer.score() > player.score()) {
                                dealerWin();
                            } else if (dealer.score() < player.score()) {
                                playerWin();
                            } else {
                                boolean isDealerBJ = dealer.isBlackJack(), isPlayerBJ = player.isBlackJack();
                                if (isDealerBJ ^ isPlayerBJ) {
                                    if (isDealerBJ) dealerWin();
                                    if (isPlayerBJ) playerWin();
                                } else {
                                    draw();
                                }
                            }
                        }
                        System.out.println(dealer.show());
                        roundOver = true;
                }
            }
        }
        scan.close();
    }

    private static void dealerWin() {
        System.out.println("Dealer win");
    }

    private static void playerWin() {
        System.out.println("You win");
    }

    private static void draw() {
        System.out.println("Draw");
    }
}
