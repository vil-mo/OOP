package ru.nsu.berezin;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.nsu.berezin.Card.CardSuit;
import ru.nsu.berezin.Card.CardValue;

class Tests {
    @Test
    void cardDeckSizeWthenDrawnAndRefilled() {
        assertEquals(new Card(CardValue.Ace, CardSuit.Clubs), new Card(CardValue.Ace, CardSuit.Clubs));

        CardDeck deck = new CardDeck();
        assertEquals(52, deck.leftCards.size());

        deck.draw();
        assertEquals(51, deck.leftCards.size());
        deck.draw();
        deck.draw();
        assertEquals(49, deck.leftCards.size());

        deck.refill();
        assertEquals(52, deck.leftCards.size());
    }

    @Test
    void cardDeckUniqueDrawn() {
        CardDeck deck = new CardDeck();
        HashSet<Card> cards = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = deck.draw();
            assertFalse(cards.contains(card));
            cards.add(card);
        }
        assertEquals(52, cards.size());
        assertEquals(0, deck.leftCards.size());
    }

    @Test
    void dealerDrawAndReset() {
        Dealer dealer = new Dealer();
        dealer.reset();
        assertEquals(52, dealer.deck.leftCards.size());
        assertEquals(0, dealer.playerHand.size());
        assertEquals(0, dealer.dealerHand.size());

        dealer.dealFirstHand();
        assertEquals(48, dealer.deck.leftCards.size());
        assertEquals(2, dealer.playerHand.size());
        assertEquals(2, dealer.dealerHand.size());
        assert(dealer.dealerHand.get(0).closed);
        assert(!dealer.dealerHand.get(1).closed);

        dealer.drawDealer();
        assertEquals(47, dealer.deck.leftCards.size());
        assertEquals(2, dealer.playerHand.size());
        assertEquals(3, dealer.dealerHand.size());

        dealer.drawPlayer();
        assertEquals(46, dealer.deck.leftCards.size());
        assertEquals(3, dealer.playerHand.size());
        assertEquals(3, dealer.dealerHand.size());

        Card[] playerHand = dealer.getPlayerHand();
        assertEquals(playerHand.length, dealer.playerHand.size());
        for (int i = 0; i < playerHand.length; i++) {
            assertEquals(playerHand[i], dealer.playerHand.get(i));
        }

        Card[] dealerHand = dealer.getDealerHand();
        assertEquals(dealerHand.length, dealer.dealerHand.size());
        for (int i = 0; i < dealerHand.length; i++) {
            assertEquals(dealerHand[i], dealer.dealerHand.get(i));
        }

        dealer.openDealerCard();
        assert(!dealer.dealerHand.get(0).closed);
    
        dealer.reset();
        assertEquals(52, dealer.deck.leftCards.size());
        assertEquals(0, dealer.playerHand.size());        
        assertEquals(0, dealer.dealerHand.size());
    }

    @Test
    void handPoints() {
        Card[] cards = new Card[3];
        for (int i = 0; i < 3; i++) {
            cards[i] = new Card(CardValue.Ace, CardSuit.Clubs);
        }

        int[] points = Dealer.handPoints(cards);
        assertEquals(1, points[0]);
        assertEquals(1, points[1]);
        assertEquals(1, points[2]);

        cards[1] = new Card(CardValue.Two, CardSuit.Spades);
        cards[2] = new Card(CardValue.Three, CardSuit.Spades);

        points = Dealer.handPoints(cards);
        assertEquals(11, points[0]);
        assertEquals(2, points[1]);
        assertEquals(3, points[2]);
    }

    @Test
    void cardValuePoints() {
        assertEquals(11, CardValue.Ace.toPointsBig());
        assertEquals(2, CardValue.Two.toPointsBig());
        assertEquals(3, CardValue.Three.toPointsBig());
        assertEquals(4, CardValue.Four.toPointsBig());
        assertEquals(5, CardValue.Five.toPointsBig());
        assertEquals(6, CardValue.Six.toPointsBig());
        assertEquals(7, CardValue.Seven.toPointsBig());
        assertEquals(8, CardValue.Eight.toPointsBig());
        assertEquals(9, CardValue.Nine.toPointsBig());
        assertEquals(10, CardValue.Ten.toPointsBig());
        assertEquals(10, CardValue.Jack.toPointsBig());
        assertEquals(10, CardValue.Queen.toPointsBig());
        assertEquals(10, CardValue.King.toPointsBig());

        assertEquals(1, CardValue.Ace.toPointsSmall());
        assertEquals(2, CardValue.Two.toPointsSmall());
        assertEquals(3, CardValue.Three.toPointsSmall());
        assertEquals(4, CardValue.Four.toPointsSmall());
        assertEquals(5, CardValue.Five.toPointsSmall());
        assertEquals(6, CardValue.Six.toPointsSmall());
        assertEquals(7, CardValue.Seven.toPointsSmall());
        assertEquals(8, CardValue.Eight.toPointsSmall());
        assertEquals(9, CardValue.Nine.toPointsSmall());
        assertEquals(10, CardValue.Ten.toPointsSmall());
        assertEquals(10, CardValue.Jack.toPointsSmall());
        assertEquals(10, CardValue.Queen.toPointsSmall());
        assertEquals(10, CardValue.King.toPointsSmall());
    }

    
}
