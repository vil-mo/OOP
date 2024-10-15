package ru.nsu.berezin.expressions;

/**
 * Expression that represents a single double number.
 */
public class Number extends Expression {
    private final double value;

    /**
     * Creates a new number expression from double value.
     * 
     * @param value Value of the number
     */
    public Number(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    public double eval(String denoting) {
        return value;
    }
}