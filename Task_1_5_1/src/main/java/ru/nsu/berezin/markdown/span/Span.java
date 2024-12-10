package ru.nsu.berezin.markdown.span;

import ru.nsu.berezin.markdown.Element;

/**
 * A span of unformatted text.
 * When serialized, the text will be transformed to remove occurrences of specail symbols ({@link #transformSpecialSymbols()}).
 */
public class Span extends Element {
    protected final String text;
    
    /**
     * Creates a span containing the given text.
     *
     * @param text text
     */
    public Span(String text) {
        this.text = text;
    }

    /**
     * Transforms special symbols in the text to have `\` before them.
     * For example, if the text is `*text*`, the transformed text will be `\*text\*`.
     * This is done to avoid problems with markdown parsers.
     * 
     * This method shouldn't be used directly, but rather by subclasses.
     * Use {@link #toString()} instead.
     * 
     * @return transformed text
     */
    public String transformSpecialSymbols() {
        return text;
    }
}