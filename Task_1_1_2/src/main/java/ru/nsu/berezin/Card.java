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
