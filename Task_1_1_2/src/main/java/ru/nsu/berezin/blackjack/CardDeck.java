package ru.nsu.berezin.blackjack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import ru.nsu.berezin.blackjack.Card.CardSuit;
import ru.nsu.berezin.blackjack.Card.CardValue;

/**
 * Represents whole deck of cards. It can store up to 52 unique cards.
 */
public class CardDeck {

    private final HashSet<Card> leftCards = new HashSet(52);
    private final Random random = new Random();

    /**
     * New deck filled with 52 cards.
     */
    public CardDeck() {
        refill();
    }

    /**
     * Refils the deck completely. This deck will have 52 cards.
     */
    public final void refill() {
        for (CardSuit suit : CardSuit.values()) {
            for (CardValue value : CardValue.values()) {
                leftCards.add(new Card(value, suit));
            }
        }
    }

    public int size() {
        return leftCards.size();
    }

    /**
     * Removes random card from the deck and returns it.
     *
     * @return card being drawn.
     * @throws IllegalStateException if deck is empty
     */
    public Card draw() throws IllegalStateException {
        if (leftCards.isEmpty()) {
            throw new IllegalStateException("Deck is empty");
        }

        int indexToDraw = random.nextInt(leftCards.size());

        Iterator<Card> iterator = leftCards.iterator();
        for (int i = 0; i < indexToDraw; i++) {
            iterator.next();
        }

        Card card = iterator.next();
        leftCards.remove(card);
        return card;
    }
}
