package ru.nsu.berezin;

/**
 * Represents single card in the deck with value and suit. 
 * It also can be closed (only affects toString call).
 */
public class Card {

    /** 
     * All possible values of a card.
    */
    public enum CardValue {
        Ace,
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
        King;

        @Override
        public String toString() {
            return switch (this) {
                case Ace -> "Туз";
                case Two -> "Двойка";
                case Three -> "Тройка";
                case Four -> "Четвёрка";
                case Five -> "Пятёрка";
                case Six -> "Шестёрка";
                case Seven -> "Семёрка";
                case Eight -> "Восьмёрка";
                case Nine -> "Девятка";
                case Ten -> "Десятка";
                case Jack -> "Валет"; 
                case Queen -> "Дама";
                case King -> "Король";
            };
        }

        /**
         * Retruns bigger value of points, when hand is not losing even with such big values.
         * In particular, it returns 11 for Ace.
         *
         * @return - points
         */
        public int toPointsBig() {
            return switch (this) {
                case Ace -> 11;
                case Two -> 2;
                case Three -> 3;
                case Four -> 4;
                case Five -> 5;
                case Six -> 6;
                case Seven -> 7;
                case Eight -> 8;
                case Nine -> 9;
                case Ten -> 10;
                case Jack -> 10;
                case Queen -> 10;
                case King -> 10;
            };
        }

        /**
         * Returns smaller value of points used when hand would loose without that downgrade.
         * In particular, it returns 1 for Ace.
         *
         * @return - points
         */
        public int toPointsSmall() {
            return switch (this) {
                case Ace -> 1;
                case Two -> 2;
                case Three -> 3;
                case Four -> 4;
                case Five -> 5;
                case Six -> 6;
                case Seven -> 7;
                case Eight -> 8;
                case Nine -> 9;
                case Ten -> 10;
                case Jack -> 10;
                case Queen -> 10;
                case King -> 10;
            };
        }
    }

    /**
     * All possible suits of a card.
     */
    public enum CardSuit {
        Hearts,
        Diamonds,
        Clubs,
        Spades;

        @Override
        public String toString() {
            return switch (this) {
                case Hearts -> "Червы";
                case Diamonds -> "Бубны";
                case Clubs -> "Крести";
                case Spades -> "Пики";
            };
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
    public boolean closed = false;

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
}
