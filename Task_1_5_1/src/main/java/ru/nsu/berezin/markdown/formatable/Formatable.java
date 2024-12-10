package ru.nsu.berezin.markdown.formatable;

/**
 * Anything that text format (italic, bold, etc.) can be applied to.
 */
public interface Formatable {
    /**
     * Returns the string representation of the span.
     *
     * @return text that should be serialized
     */
    String serialized();
}
