package ru.nsu.berezin.expressions;

import java.io.IOException;
import java.io.Reader;

/**
 * Parser for expressions.
 */
public class ExpressionParser {

    int toPutBack = -1;



    /**
     * Parses expression from reader.
     * 
     * @param expression Expression to parse
     * @return Null if sequence is not a valid expression.
     */
    public Expression parse(Reader expression) {
        try {
            while (expression.ready()) {
                char c = getNext(expression);

                if (c == '(') {
                    Expression left = parse(expression);
                    if (left == null) {
                        return null;
                    }

                    char next = getNext(expression);

                    if (next == '+') {
                        Expression right = parse(expression);
                        if (right == null) {
                            return null;
                        }

                        char next2 = getNext(expression);
                        if (next2 != ')') {
                            return null;
                        }
                        putBack(next2);

                        return new Add(left, right);
                    }
                    return null;
                }
                if (Character.isLetter(c)) {
                    StringBuilder sb = new StringBuilder();
                    while (Character.isLetter(c)) {
                        sb.append(c);
                        c = (char) expression.read();
                    }
                    putBack(c);
                    return new Variable(sb.toString());
                }
                if (Character.isDigit(c)) {
                    StringBuilder sb = new StringBuilder();
                    while (Character.isDigit(c)) {
                        sb.append(c);
                        c = (char) expression.read();
                    }
                    if (c == '.') {
                        sb.append(c);
                        c = (char) expression.read();
                        while (Character.isDigit(c)) {
                            sb.append(c);
                            c = (char) expression.read();
                        }
                    }
                    putBack(c);
                    return new Number(Double.parseDouble(sb.toString()));
                }
            }
        } catch (IOException e) {
            return null;
        }

        return null;
    }

    private void putBack(char c) {
        this.toPutBack = (int)c;
    }

    private char getNext(Reader expression) throws IOException {
        char c;
        if (toPutBack != -1) {
            c = (char) toPutBack;
            toPutBack = -1;
        } else {
            c = (char) expression.read();
        }

        while (Character.isWhitespace(c)) {
            c = (char) expression.read();
        }
        return c;
    }
}