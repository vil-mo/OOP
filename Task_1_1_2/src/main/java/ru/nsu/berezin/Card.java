package ru.nsu.berezin;

public class Card {

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

    public final CardValue value;
    public final CardSuit suit;
    public boolean closed = false;

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
