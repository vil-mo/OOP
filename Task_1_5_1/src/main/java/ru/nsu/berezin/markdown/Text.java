package ru.nsu.berezin.markdown;

import java.util.Arrays;
import java.util.List;

import ru.nsu.berezin.markdown.span.Span;

/**
 * A collection of spans. Shloud be used where you want to show text.
 */
public class Text extends Element {

    private final List<Span> spans = new java.util.ArrayList<>();

    /**
     * Creates an empty text.
     */
    public Text() {
    }

    /**
     * Creates a text with a single unformatted span.
     *
     * @param text text
     */
    public Text(String text) {
        spans.add(new Span(text));
    }

    /**
     * Creates a text from the given spans.
     *
     * @param spans spans
     */
    public Text(Span... spans) {
        this.spans.addAll(Arrays.asList(spans));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Span span : spans) {
            builder.append(span.toString());
        }
        return builder.toString();
    }
}
