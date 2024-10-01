package ru.nsu.berezin.expressions;

/**
 * Expression that represents addition of two expressions.
 */
public class Add extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Creates a new add expression.
     * 
     * @param left Left expression
     * @param right Right expression
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String print() {
        return "(" + left.print() + " + " + right.print() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    @Override
    public double eval(String denoting) {
        return left.eval(denoting) + right.eval(denoting);
    }
}