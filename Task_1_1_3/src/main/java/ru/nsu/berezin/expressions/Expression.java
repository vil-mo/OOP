package ru.nsu.berezin.expressions;

import java.text.ParseException;

/**
 * Expression class that can have any number of variables.
 */
public abstract class Expression {
    /**
     * Parses expression from string.
     *
     * @param expression String to parse
     * @return Parsed expression
     * @throws ParseException if expression is not valid
     */
    public static Expression parse(String expression) throws ParseException {
        ExpressionParser parser = new ExpressionParser(expression);
        return parser.parse();
    }

    /**
     * Returns string representation of expression.
     *
     * @return String representation of expression
     */
    public String print() {
        return toString();
    }

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
