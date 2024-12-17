package ru.nsu.berezin.markdown;

import ru.nsu.berezin.markdown.formatable.Format;


/**
 * A paragraph.
 * Each element of the paragraph is separated by a new line.
 */
public class Paragraph extends Element {
    private final java.util.List<Format> elements;

    /**
     * Creates a paragraph with the given elements.
     *
     * @param elements elements
     */
    public Paragraph(Format... elements) {
        this.elements = java.util.Arrays.asList(elements);
    }

    /**
     * Returns the length of the text that will be appended to the builder when calling serialized.
     *
     * @return length in characters
     */
    public int lenght() {
        return elements.stream().mapToInt(Format::length).sum();
    }

    @Override
    public void serialized(StringBuilder builder) {
        for (Format element : elements) {
            element.serialized(builder);
        }
    }
}