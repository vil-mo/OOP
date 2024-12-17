package ru.nsu.berezin.markdown.formatable;

import ru.nsu.berezin.markdown.Serialized;

/**
 * Anything that text format (italic, bold, etc.) can be applied to. Formattable
 * shouldn't store any formatting information, but rather just return the text
 * that should be serialized.
 *
 * Formattable shouldn't store any {@link ru.nsu.berezin.markdown.Element}, only
 * other {@link Formatable}s.
 */
public interface Formatable extends Serialized {
    /**
     * Returns the length of the text that will be appended to the builder when calling serialized.
     *
     * @return length in characters
     */
    int length();

}
