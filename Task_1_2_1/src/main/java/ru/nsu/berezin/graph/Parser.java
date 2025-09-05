package ru.nsu.berezin.graph;

import java.text.ParseException;

/**
 * Indicates that a class can parse a string to a type.
 * 
 * @param <T> Type of the result
 */
public interface Parser<T> {
    public T parse(String input) throws ParseException;
}