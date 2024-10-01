package ru.nsu.berezin.expressions;

public abstract class Expression {
    public static Expression parse(String expression) {
        return null;
    }

    public abstract String print();

    public abstract Expression derivative(String variable);

    public abstract double eval(String denoting);
}