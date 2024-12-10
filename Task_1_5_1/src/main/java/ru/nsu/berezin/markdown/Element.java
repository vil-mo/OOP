package ru.nsu.berezin.markdown;

/**
 * An element of a markdown document.
 */
public abstract class Element {
    /**
     * Returns the string representation of the element.
     *
     * @return text that should be serialized
     */
    public abstract String serialized();
}