package ru.nsu.berezin.expressions;

/**
 * Expression that represents subtraction of two expressions.
 */
public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Creates a new sub expression.
     * 
     * @param left Left expression
     * @param right Right expression
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " - " + right.toString() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public double eval(String denoting) {
        return left.eval(denoting) - right.eval(denoting);
    }
}