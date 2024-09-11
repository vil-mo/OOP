package ru.nsu.berezin;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

import ru.nsu.berezin.Card.CardSuit;
import ru.nsu.berezin.Card.CardValue;

public class CardDeck {

    HashSet<Card> leftCards = new HashSet(52);
    Random random = new Random();

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

    /**
     * Removes random card from the deck and returns it.
     *
     *
     * @return card being drawn.
     */
    public Card draw() {
        int index_to_draw = random.nextInt(leftCards.size());

        Iterator<Card> iterator = leftCards.iterator();
        for (int i = 0; i < index_to_draw; i++) {
            iterator.next();
        }

        Card card = iterator.next();
        leftCards.remove(card);
        return card;
    }
}
