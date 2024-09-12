package ru.nsu.berezin;

import java.util.ArrayList;

public class Dealer {

    CardDeck deck = new CardDeck();

    ArrayList<Card> playerHand = new ArrayList();
    ArrayList<Card> dealerHand = new ArrayList();

    public Dealer() {
        reset();
    }

    public final void reset() {
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

    public Card[] getDealerHand() {
        return (Card[]) dealerHand.toArray();
    }

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
