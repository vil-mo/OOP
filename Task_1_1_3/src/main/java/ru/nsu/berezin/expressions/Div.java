package ru.nsu.berezin.expressions;

/**
 * Expression that represents division of two expressions.
 */
public class Div extends Expression {

    private final Expression left;
    private final Expression right;

    /**
     * Creates a new div expression.
     *
     * @param left Left expression
     * @param right Right expression
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " / " + right.toString() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Div(
                new Sub(
                        new Mul(left.derivative(variable), right),
                        new Mul(left, right.derivative(variable)
                        )
                ), new Mul(right, right));
    }

    @Override
    public double eval(String denoting) {
        return left.eval(denoting) / right.eval(denoting);
    }
}
