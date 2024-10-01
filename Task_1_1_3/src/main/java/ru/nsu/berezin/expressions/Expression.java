package ru.nsu.berezin.expressions;

import java.io.Reader;

/**
 * Expression class that can have any number of variables.
 */
public abstract class Expression {
    /**
     * Parses expression from string.
     *
     * @param expression String to parse
     * @return Null if string is not a valid expression.
     */
    public static Expression parse(Reader expression) {
        ExpressionParser parser = new ExpressionParser();
        return parser.parse(expression);
    }

    /**
     * Returns string representation of expression.
     *
     * @return String representation of expression
     */
    public abstract String print();

    /**
     * Returns derivative of expression with respect to variable.
     *
     * @param variable Variable to derivate with
     * @return New expression
     */
    public abstract Expression derivative(String variable);

    /**
     * Evaluates expression with variables denoting.
     *
     * @param denoting String that has the form of "x = 1; y = 2"
     * @return Value of expression
     * @throws IllegalArgumentException if at least one variable is not found in
     *     denoting or incorrect denoting format is passed
     */
    public abstract double eval(String denoting) throws IllegalArgumentException;
}
