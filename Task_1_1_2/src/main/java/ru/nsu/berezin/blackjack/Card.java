package ru.nsu.berezin.blackjack;

/**
 * Represents single card in the deck with value and suit. 
 * It also can be closed (only affects toString call).
 */
public class Card {

    /** 
     * All possible values of a card.
    */
    public enum CardValue {
        Ace("Туз", 11, 1),
        Two("Двойка", 2, 2),
        Three("Тройка", 3, 3),
        Four("Четвёрка", 4, 4),
        Five("Пятёрка", 5, 5),
        Six("Шестёрка", 6, 6),
        Seven("Семёрка", 7, 7),
        Eight("Восьмёрка", 8, 8),
        Nine("Девятка", 9, 9),
        Ten("Десятка", 10, 10),
        Jack("Валет", 10, 10),
        Queen("Дама", 10, 10),
        King("Король", 10, 10);

        private final String name;
        private final int pointsBig;
        private final int pointsSmall;

        CardValue(String name, int pointsBig, int pointsSmall) {
            this.name = name;
            this.pointsBig = pointsBig;
            this.pointsSmall = pointsSmall;
        }

        @Override
        public String toString() {
            return name;
        }

        /**
         * Retruns bigger value of points, when hand is not losing even with such big values.
         * In particular, it returns 11 for Ace.
         *
         * @return - points
         */
        public int getPointsBig() {
            return pointsBig;
        }

        /**
         * Returns smaller value of points used when hand would loose without that downgrade.
         * In particular, it returns 1 for Ace.
         *
         * @return - points
         */
        public int getPointsSmall() {
            return pointsSmall;
        }
    }

    /**
     * All possible suits of a card.
     */
    public enum CardSuit {
        Hearts("Червы"),
        Diamonds("Бубны"),
        Clubs("Крести"), 
        Spades("Пики");

        private final String name;

        CardSuit(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Value of a card.
     */
    public final CardValue value;
    /**
     * Suit of a card.
     */
    public final CardSuit suit;
    /**
     * If true, the card is closed and toString will return {@literal <закрытая карта>}
     * Default is false.
     */
    private boolean closed = false;

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * New open card with provided value and suit.
     *
     * @param value - value of the card
     * @param suit - suit of the card
     */
    public Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        if (closed) {
            return "<закрытая карта>";
        }
        return value.toString() + " " + suit.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return value == card.value && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return value.ordinal() * 13 + suit.ordinal();
    }
}
