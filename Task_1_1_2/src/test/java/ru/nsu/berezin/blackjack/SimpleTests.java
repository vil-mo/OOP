package ru.nsu.berezin.blackjack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.berezin.blackjack.Card.CardSuit;
import ru.nsu.berezin.blackjack.Card.CardValue;

class SimpleTests {

    @Test
    void cardDeckSizeWthenDrawnAndRefilled() {
        Assertions.assertEquals(
                new Card(CardValue.Ace, CardSuit.Clubs),
                new Card(CardValue.Ace, CardSuit.Clubs)
        );

        CardDeck deck = new CardDeck();
        Assertions.assertEquals(52, deck.size());

        deck.draw();
        Assertions.assertEquals(51, deck.size());
        deck.draw();
        deck.draw();
        Assertions.assertEquals(49, deck.size());

        deck.refill();
        Assertions.assertEquals(52, deck.size());
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
        Assertions.assertEquals(0, deck.size());
    }

    @Test
    void dealerDrawAndReset() {
        Dealer dealer = new Dealer();
        dealer.reset();
        Assertions.assertEquals(52, dealer.getCardDeck().size());
        Assertions.assertEquals(0, dealer.getPlayerHand().size());
        Assertions.assertEquals(0, dealer.getDealerHand().size());

        dealer.dealFirstHand();
        Assertions.assertEquals(48, dealer.getCardDeck().size());
        Assertions.assertEquals(2, dealer.getPlayerHand().size());
        Assertions.assertEquals(2, dealer.getDealerHand().size());
        assert dealer.getPlayerHand().get(0).isClosed();
        assert !dealer.getDealerHand().get(1).isClosed();

        dealer.drawDealer();
        Assertions.assertEquals(47, dealer.getCardDeck().size());
        Assertions.assertEquals(2, dealer.getPlayerHand().size());
        Assertions.assertEquals(3, dealer.getDealerHand().size());

        dealer.drawPlayer();
        Assertions.assertEquals(46, dealer.getCardDeck().size());
        Assertions.assertEquals(3, dealer.getPlayerHand().size());
        Assertions.assertEquals(3, dealer.getDealerHand().size());

        dealer.openDealerCard();
        assert !dealer.getDealerHand().get(0).isClosed();

        dealer.reset();
        Assertions.assertEquals(52, dealer.getCardDeck().size());
        Assertions.assertEquals(0, dealer.getPlayerHand().size());
        Assertions.assertEquals(0, dealer.getDealerHand().size());
    }

    @Test
    void handPoints() {
        List<Card> cards = new ArrayList(3);
        for (int i = 0; i < 3; i++) {
            cards.add(new Card(CardValue.Ace, CardSuit.Clubs));
        }

        List<Integer> points = Dealer.handPoints(cards);
        Assertions.assertEquals(1, points.get(0).intValue());
        Assertions.assertEquals(1, points.get(1).intValue());
        Assertions.assertEquals(1, points.get(2).intValue());

        cards.set(1, new Card(CardValue.Two, CardSuit.Spades));
        cards.set(2, new Card(CardValue.Three, CardSuit.Spades));

        points = Dealer.handPoints(cards);
        Assertions.assertEquals(11, points.get(0).intValue());
        Assertions.assertEquals(2, points.get(1).intValue());
        Assertions.assertEquals(3, points.get(2).intValue());
    }

    @Test
    void cardValuePoints() {
        Assertions.assertEquals(11, CardValue.Ace.getPointsBig());
        Assertions.assertEquals(2, CardValue.Two.getPointsBig());
        Assertions.assertEquals(3, CardValue.Three.getPointsBig());
        Assertions.assertEquals(4, CardValue.Four.getPointsBig());
        Assertions.assertEquals(5, CardValue.Five.getPointsBig());
        Assertions.assertEquals(6, CardValue.Six.getPointsBig());
        Assertions.assertEquals(7, CardValue.Seven.getPointsBig());
        Assertions.assertEquals(8, CardValue.Eight.getPointsBig());
        Assertions.assertEquals(9, CardValue.Nine.getPointsBig());
        Assertions.assertEquals(10, CardValue.Ten.getPointsBig());
        Assertions.assertEquals(10, CardValue.Jack.getPointsBig());
        Assertions.assertEquals(10, CardValue.Queen.getPointsBig());
        Assertions.assertEquals(10, CardValue.King.getPointsBig());

        Assertions.assertEquals(1, CardValue.Ace.getPointsSmall());
        Assertions.assertEquals(2, CardValue.Two.getPointsSmall());
        Assertions.assertEquals(3, CardValue.Three.getPointsSmall());
        Assertions.assertEquals(4, CardValue.Four.getPointsSmall());
        Assertions.assertEquals(5, CardValue.Five.getPointsSmall());
        Assertions.assertEquals(6, CardValue.Six.getPointsSmall());
        Assertions.assertEquals(7, CardValue.Seven.getPointsSmall());
        Assertions.assertEquals(8, CardValue.Eight.getPointsSmall());
        Assertions.assertEquals(9, CardValue.Nine.getPointsSmall());
        Assertions.assertEquals(10, CardValue.Ten.getPointsSmall());
        Assertions.assertEquals(10, CardValue.Jack.getPointsSmall());
        Assertions.assertEquals(10, CardValue.Queen.getPointsSmall());
        Assertions.assertEquals(10, CardValue.King.getPointsSmall());
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
