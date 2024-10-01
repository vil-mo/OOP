package ru.nsu.berezin.expressions;

/**
 * Expression that represents multiplication of two expressions.
 */
public class Mul extends Expression {
    private final Expression left;
    private final Expression right;

    /**
     * Creates a new mul expression.
     * 
     * @param left Left expression
     * @param right Right expression
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String print() {
        return "(" + left.print() + " * " + right.print() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(new Mul(left, right.derivative(variable)), new Mul(left.derivative(variable), right));
    }

    @Override
    public double eval(String denoting) {
        return left.eval(denoting) * right.eval(denoting);
    }
}