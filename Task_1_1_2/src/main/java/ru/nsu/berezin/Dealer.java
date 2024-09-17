package ru.nsu.berezin;

import java.util.ArrayList;

/**
 * Dealer has a deck and can draw cards from the deck for player and dealer
 * itself.
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
     *
     *
     * @return drawn card
     */
    public Card drawPlayer() {
        final Card drawn = deck.draw();
        playerHand.add(drawn);
        return drawn;
    }

    /**
     * Draw random card in the dealer's hand. If it's the first card of the
     * dealer, it will be closed.
     *
     *
     * @return drawn card
     */
    public Card drawDealer() {
        Card drawn = deck.draw();
        if (dealerHand.isEmpty()) {
            drawn.closed = true;
        }
        dealerHand.add(drawn);
        return drawn;
    }

    /**
     * Deals the first hand of the round. It draws two cards for player and two
     * cards for dealer.
     */
    public void dealFirstHand() {
        drawPlayer();
        drawPlayer();
        drawDealer();
        drawDealer();
    }

    /**
     * Opens the closed card of the dealer.
     * 
     * 
     * @return - opened card
     * @throws IndexOutOfBoundsException - if dealer's hand is empty
     */
    public Card openDealerCard() throws IndexOutOfBoundsException {
        Card card = dealerHand.get(0);
        card.closed = false;
        return card;
    }

    /**
     * Returns player's hand. This clones hand's cards.
     * 
     * 
     * @return - player's hand
     */
    public Card[] getPlayerHand() {
        Card[] array = new Card[playerHand.size()];
        return playerHand.toArray(array);
    }

    /**
     * Returns dealer's hand. This clones hand's cards. If `openDealerCard`
     * wasn't called, first card will be closed.
     * 
     * 
     * @return - dealer's hand
     */
    public Card[] getDealerHand() {
        Card[] array = new Card[dealerHand.size()];
        return dealerHand.toArray(array);
    }

    /**
     * Get the point value of the hand of cards.
     * 
     * 
     * @param cards - cards to get points from
     * @return - For each card in the array, it's point value is at the same index in the array.
     */
    public static int[] handPoints(Card[] cards) {
        int[] points = new int[cards.length];

        int sum = 0;

        int i = 0;
        for (Card card : cards) {
            int cardPoints = card.value.toPointsBig();
            points[i] = cardPoints;
            sum += cardPoints;

            i++;
        }

        if (sum > 21) {
            i = 0;
            for (Card card : cards) {
                int cardPoints = card.value.toPointsSmall();
                points[i] = cardPoints;

                i++;
            }
        }

        return points;
    }
}
