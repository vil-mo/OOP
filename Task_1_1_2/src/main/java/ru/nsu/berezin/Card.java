package ru.nsu.berezin;

public class Card {
    enum CardValue {
        Ace,
        One,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
    }

    enum CardSuit {
        Hearts,
        Diamonds,
        Clubs,
        Spades,
    }

    private CardValue value;
    private CardSuit suit;

    Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }
}
