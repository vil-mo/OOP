package ru.nsu.berezin.graph;

import java.text.ParseException;

/**
 * Parses integers using `Integer.valueOf`.
 */
public class IntParser implements Parser<Integer> {
    @Override
    public Integer parse(String input) throws ParseException {
        try {
            return Integer.valueOf(input);
        } catch (NumberFormatException e) {
            throw new ParseException("Not an integer", 0);
        }
    }
}
