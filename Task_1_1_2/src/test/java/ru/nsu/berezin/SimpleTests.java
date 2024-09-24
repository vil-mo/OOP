package ru.nsu.berezin;

import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.nsu.berezin.Card.CardSuit;
import ru.nsu.berezin.Card.CardValue;

class SimpleTests {

    @Test
    void cardDeckSizeWthenDrawnAndRefilled() {
        Assertions.assertEquals(
                new Card(CardValue.Ace, CardSuit.Clubs),
                new Card(CardValue.Ace, CardSuit.Clubs)
        );

        CardDeck deck = new CardDeck();
        Assertions.assertEquals(52, deck.leftCards.size());

        deck.draw();
        Assertions.assertEquals(51, deck.leftCards.size());
        deck.draw();
        deck.draw();
        Assertions.assertEquals(49, deck.leftCards.size());

        deck.refill();
        Assertions.assertEquals(52, deck.leftCards.size());
    }

    @Test
    void cardDeckUniqueDrawn() {
        CardDeck deck = new CardDeck();
        HashSet<Card> cards = new HashSet<>();

        for (int i = 0; i < 52; i++) {
            Card card = deck.draw();
            Assertions.assertFalse(cards.contains(card));
            cards.add(card);
        }
        Assertions.assertEquals(52, cards.size());
        Assertions.assertEquals(0, deck.leftCards.size());
    }

    @Test
    void dealerDrawAndReset() {
        Dealer dealer = new Dealer();
        dealer.reset();
        Assertions.assertEquals(52, dealer.deck.leftCards.size());
        Assertions.assertEquals(0, dealer.playerHand.size());
        Assertions.assertEquals(0, dealer.dealerHand.size());

        dealer.dealFirstHand();
        Assertions.assertEquals(48, dealer.deck.leftCards.size());
        Assertions.assertEquals(2, dealer.playerHand.size());
        Assertions.assertEquals(2, dealer.dealerHand.size());
        assert dealer.dealerHand.get(0).closed;
        assert !dealer.dealerHand.get(1).closed;

        dealer.drawDealer();
        Assertions.assertEquals(47, dealer.deck.leftCards.size());
        Assertions.assertEquals(2, dealer.playerHand.size());
        Assertions.assertEquals(3, dealer.dealerHand.size());

        dealer.drawPlayer();
        Assertions.assertEquals(46, dealer.deck.leftCards.size());
        Assertions.assertEquals(3, dealer.playerHand.size());
        Assertions.assertEquals(3, dealer.dealerHand.size());

        Card[] playerHand = dealer.getPlayerHand();
        Assertions.assertEquals(playerHand.length, dealer.playerHand.size());
        for (int i = 0; i < playerHand.length; i++) {
            Assertions.assertEquals(playerHand[i], dealer.playerHand.get(i));
        }

        Card[] dealerHand = dealer.getDealerHand();
        Assertions.assertEquals(dealerHand.length, dealer.dealerHand.size());
        for (int i = 0; i < dealerHand.length; i++) {
            Assertions.assertEquals(dealerHand[i], dealer.dealerHand.get(i));
        }

        dealer.openDealerCard();
        assert !dealer.dealerHand.get(0).closed;

        dealer.reset();
        Assertions.assertEquals(52, dealer.deck.leftCards.size());
        Assertions.assertEquals(0, dealer.playerHand.size());
        Assertions.assertEquals(0, dealer.dealerHand.size());
    }

    @Test
    void handPoints() {
        Card[] cards = new Card[3];
        for (int i = 0; i < 3; i++) {
            cards[i] = new Card(CardValue.Ace, CardSuit.Clubs);
        }

        int[] points = Dealer.handPoints(cards);
        Assertions.assertEquals(1, points[0]);
        Assertions.assertEquals(1, points[1]);
        Assertions.assertEquals(1, points[2]);

        cards[1] = new Card(CardValue.Two, CardSuit.Spades);
        cards[2] = new Card(CardValue.Three, CardSuit.Spades);

        points = Dealer.handPoints(cards);
        Assertions.assertEquals(11, points[0]);
        Assertions.assertEquals(2, points[1]);
        Assertions.assertEquals(3, points[2]);
    }

    @Test
    void cardValuePoints() {
        Assertions.assertEquals(11, CardValue.Ace.toPointsBig());
        Assertions.assertEquals(2, CardValue.Two.toPointsBig());
        Assertions.assertEquals(3, CardValue.Three.toPointsBig());
        Assertions.assertEquals(4, CardValue.Four.toPointsBig());
        Assertions.assertEquals(5, CardValue.Five.toPointsBig());
        Assertions.assertEquals(6, CardValue.Six.toPointsBig());
        Assertions.assertEquals(7, CardValue.Seven.toPointsBig());
        Assertions.assertEquals(8, CardValue.Eight.toPointsBig());
        Assertions.assertEquals(9, CardValue.Nine.toPointsBig());
        Assertions.assertEquals(10, CardValue.Ten.toPointsBig());
        Assertions.assertEquals(10, CardValue.Jack.toPointsBig());
        Assertions.assertEquals(10, CardValue.Queen.toPointsBig());
        Assertions.assertEquals(10, CardValue.King.toPointsBig());

        Assertions.assertEquals(1, CardValue.Ace.toPointsSmall());
        Assertions.assertEquals(2, CardValue.Two.toPointsSmall());
        Assertions.assertEquals(3, CardValue.Three.toPointsSmall());
        Assertions.assertEquals(4, CardValue.Four.toPointsSmall());
        Assertions.assertEquals(5, CardValue.Five.toPointsSmall());
        Assertions.assertEquals(6, CardValue.Six.toPointsSmall());
        Assertions.assertEquals(7, CardValue.Seven.toPointsSmall());
        Assertions.assertEquals(8, CardValue.Eight.toPointsSmall());
        Assertions.assertEquals(9, CardValue.Nine.toPointsSmall());
        Assertions.assertEquals(10, CardValue.Ten.toPointsSmall());
        Assertions.assertEquals(10, CardValue.Jack.toPointsSmall());
        Assertions.assertEquals(10, CardValue.Queen.toPointsSmall());
        Assertions.assertEquals(10, CardValue.King.toPointsSmall());
    }

    @Test
    void toStringTests() {
        Assertions.assertEquals("Туз", CardValue.Ace.toString());
        Assertions.assertEquals("Двойка", CardValue.Two.toString());
        Assertions.assertEquals("Тройка", CardValue.Three.toString());
        Assertions.assertEquals("Четвёрка", CardValue.Four.toString());
        Assertions.assertEquals("Пятёрка", CardValue.Five.toString());
        Assertions.assertEquals("Шестёрка", CardValue.Six.toString());
        Assertions.assertEquals("Семёрка", CardValue.Seven.toString());
        Assertions.assertEquals("Восьмёрка", CardValue.Eight.toString());
        Assertions.assertEquals("Девятка", CardValue.Nine.toString());
        Assertions.assertEquals("Десятка", CardValue.Ten.toString());
        Assertions.assertEquals("Валет", CardValue.Jack.toString());
        Assertions.assertEquals("Дама", CardValue.Queen.toString());
        Assertions.assertEquals("Король", CardValue.King.toString());

        Assertions.assertEquals("Червы", CardSuit.Hearts.toString());
        Assertions.assertEquals("Бубны", CardSuit.Diamonds.toString());
        Assertions.assertEquals("Крести", CardSuit.Clubs.toString());
        Assertions.assertEquals("Пики", CardSuit.Spades.toString());

        Assertions.assertEquals(
                "Туз Червы", 
                new Card(CardValue.Ace, CardSuit.Hearts).toString()
        );
    }
}
