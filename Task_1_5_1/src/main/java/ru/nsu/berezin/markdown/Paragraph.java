package ru.nsu.berezin.markdown;

import ru.nsu.berezin.markdown.formatable.Format;
import ru.nsu.berezin.markdown.formatable.Formatable;
import ru.nsu.berezin.markdown.formatable.Text;

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
     * Creates a paragraph with unformatted formatable type.
     *
     * @param text formattable type
     */
    public Paragraph(Formatable text) {
        this(new Format(text));
    }

    /**
     * Creates a paragraph with the given text.
     *
     * @param text text
     */
    public Paragraph(String text) {
        this(new Format(new Text(text)));
    }

    @Override
    public void serialized(StringBuilder builder) {
        for (Format element : elements) {
            element.serialized(builder);
        }
    }
}