package ru.nsu.berezin.expressions;

import java.text.ParseException;

/**
 * Parser for expressions.
 */
public class ExpressionParser {

    private final String expression;
    private int currentChar = 0;

    /**
     * Creates a new expression parser.
     *
     * @param expression Expression to parse
     */
    public ExpressionParser(String expression) {
        this.expression = expression;
    }

    /**
     * Parses given expression.
     *
     * @return Parsed expression
     * @throws ParseException if expression is not valid
     */
    public Expression parse() throws ParseException {
        char c;
        c = getNext();

        if (c == '(') {
            Expression left = parse();

            char operation = getNext();
            Expression right = parse();

            char closingBracket = getNext();
            if (closingBracket != ')') {
                throw new ParseException("Unexpected character '" + closingBracket + "', expected ')'", currentChar);
            }

            return switch (operation) {
                case '+' ->
                    new Add(left, right);
                case '-' ->
                    new Sub(left, right);
                case '*' ->
                    new Mul(left, right);
                case '/' ->
                    new Div(left, right);

                default ->
                    throw new ParseException("Illegal operation '" + operation + "'", currentChar);
            };
        }
        if (Character.isLetter(c)) {
            StringBuilder sb = new StringBuilder();
            currentChar--;

            while (Character.isLetter(c)) {
                currentChar++;
                sb.append(c);
                if (currentChar >= expression.length()) {
                    break;
                }
                c = expression.charAt(currentChar);
            }
            return new Variable(sb.toString());
        }
        if (Character.isDigit(c)) {
            StringBuilder sb = new StringBuilder();
            currentChar--;

            while (Character.isDigit(c)) {
                currentChar++;
                sb.append(c);
                if (currentChar >= expression.length()) {
                    break;
                }
                c = expression.charAt(currentChar);
            }
            if (c == '.') {
                currentChar++;
                sb.append(c);

                if (currentChar < expression.length()) {
                    c = expression.charAt(currentChar);   
                }
                while (Character.isDigit(c)) {
                    currentChar++;
                    sb.append(c);
                    if (currentChar >= expression.length()) {
                        break;
                    }
                    c = expression.charAt(currentChar);
                }
            }
            return new Number(Double.parseDouble(sb.toString()));
        }

        throw new ParseException("Unexpected character", currentChar);
    }

    /**
     * Returns operation character in the given string, skipping whitespaces.
     *
     * @return Next character
     * @throws IndexOutOfBoundsException if there is no more characters in the
     * string
     */
    private char getNext() throws ParseException {
        char c;

        try {
            c = expression.charAt(currentChar);
            currentChar++;

            while (Character.isWhitespace(c)) {
                c = expression.charAt(currentChar);
                currentChar++;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException("Unexpected end of expression", currentChar);
        }
        return c;
    }
}
