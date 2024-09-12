package ru.nsu.berezin;

import java.util.ArrayList;


public class Dealer {
    CardDeck deck = new CardDeck();

    ArrayList<Card> playerHand = new ArrayList();
    ArrayList<Card> dealerHand = new ArrayList();

    public void reset() {
        this.deck.refill();
        playerHand.clear();
        dealerHand.clear();
    }

    public void drawPlayer() {
        final Card drawn = deck.draw();
        playerHand.add(drawn);
    }

    public void drawDealer() {
        Card drawn = deck.draw();
        if (dealerHand.isEmpty()) {
            drawn.closed = true;
        }
        dealerHand.add(drawn);
    }

    public void openDealerCard() {
        dealerHand.get(0).closed = false;
    }

    public Card[] getPlayerHand() {
        return (Card[]) playerHand.toArray();
    }
}