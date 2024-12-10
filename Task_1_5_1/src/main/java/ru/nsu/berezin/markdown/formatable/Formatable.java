package ru.nsu.berezin.markdown.formatable;

/**
 * Anything that text format (italic, bold, etc.) can be applied to.
 * Formattable shouldn't store any formatting information, but rather just
 * return the text that should be serialized.
 * 
 * Formattable shouldn't store any {@link ru.nsu.berezin.markdown.Element}, only other {@link Formatable}s.
 */
public interface Formatable {
    /**
     * Returns the string representation of the span.
     *
     * @return text that should be serialized
     */
    String serialized();
}
