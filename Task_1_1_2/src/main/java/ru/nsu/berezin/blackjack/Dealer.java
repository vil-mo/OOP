package ru.nsu.berezin.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Dealer has a deck and can draw cards from the deck for player and dealer
 * itself.
 */
public class Dealer {

    private final CardDeck deck = new CardDeck();

    private final List<Card> playerHand = new ArrayList();
    private final List<Card> dealerHand = new ArrayList();

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
     * @return drawn card
     */
    public Card drawDealer() {
        Card drawn = deck.draw();
        if (dealerHand.isEmpty()) {
            drawn.setClosed(true);
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
     * @return - opened card
     * @throws IndexOutOfBoundsException - if dealer's hand is empty
     */
    public Card openDealerCard() throws IndexOutOfBoundsException {
        Card card = dealerHand.get(0);
        card.setClosed(false);
        return card;
    }

    /**
     * Returns player's hand.
     *
     * @return - player's hand
     */
    public List<Card> getPlayerHand() {
        return playerHand;
    }

    /**
     * Returns dealer's hand. If `openDealerCard`
     * wasn't called, first card will be closed.
     *
     * @return - dealer's hand
     */
    public List<Card> getDealerHand() {
        return dealerHand;
    }

    CardDeck getCardDeck() {
        return deck;
    }

    /**
     * Get the point value of the hand of cards.
     *
     * @param cards - cards to get points from
     * @return - For each card in the array, it's point value is at the same index in the array.
     */
    public static List<Integer> handPoints(List<Card> cards) {
        List<Integer> points = new ArrayList(cards.size());

        int sum = 0;
        for (Card card : cards) {
            int cardPoints = card.value.getPointsBig();
            points.add(cardPoints);
            sum += cardPoints;
        }

        if (sum > 21) {
            int i = 0;
            for (Card card : cards) {
                int cardPoints = card.value.getPointsSmall();
                points.set(i, cardPoints);

                i++;
            }
        }

        return points;
    }
}
