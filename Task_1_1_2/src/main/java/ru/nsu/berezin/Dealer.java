package ru.nsu.berezin;

import java.util.ArrayList;

/**
 * Dealer has a deck and can draw cards from the deck for player and dealer itself.
 */
public class Dealer {

    CardDeck deck = new CardDeck();

    ArrayList<Card> playerHand = new ArrayList();
    ArrayList<Card> dealerHand = new ArrayList();

    /**
     * New diller that haven't dealt any cards to anyone.
     */
    public Dealer() {
        reset();
    }

    /**
     * Return all cards back to the deck.
     */
    public final void reset() {
        this.deck.refill();
        playerHand.clear();
        dealerHand.clear();
    }

    /**
     * Draw random card in the player's hand.
     */
    public void drawPlayer() {
        final Card drawn = deck.draw();
        playerHand.add(drawn);
    }


    /**
     * Draw random card in the dealer's hand.
     * If it's the first card of the dealer, it will be closed.
     */
    public void drawDealer() {
        Card drawn = deck.draw();
        if (dealerHand.isEmpty()) {
            drawn.closed = true;
        }
        dealerHand.add(drawn);
    }

    /**
     * Opens the closed card of the dealer.
     * Throws exception if dealer's hand is empty. 
     */
    public void openDealerCard() {
        dealerHand.get(0).closed = false;
    }

    /**
     * Returns player's hand. This clones hand's cards.
     */
    public Card[] getPlayerHand() {
        return (Card[]) playerHand.toArray();
    }

    /**
     * Returns dealer's hand. This clones hand's cards.
     * If `openDealerCard` wasn't called, first card will be closed.
     */
    public Card[] getDealerHand() {
        return (Card[]) dealerHand.toArray();
    }

    /**
     * Get the point value of the hand of cards.
     */
    public static int[] handPoints(Card[] cards) {
        int[] points = new int[cards.length];

        int sum = 0;

        int i = 0;
        for (Card card : cards) {
            int card_points = card.value.toPointsBig();
            points[i] = card_points;
            sum += card_points;

            i++;
        }

        if (sum > 21) {
            i = 0;
            for (Card card : cards) {
                int card_points = card.value.toPointsSmall();
                points[i] = card_points;

                i++;
            }
        }

        return points;
    }
}
