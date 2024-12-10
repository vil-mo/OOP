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

    @Override
    public String serialized() {
        StringBuilder builder = new StringBuilder();
        for (Format element : elements) {
            builder.append(element.serialized());
        }
        builder.append("\n");
        return builder.toString();
    }
}