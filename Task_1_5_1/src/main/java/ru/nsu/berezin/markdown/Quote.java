package ru.nsu.berezin.markdown;

/**
 * A quote. Formatted with `>`.
 */
public class Quote {
    private final Paragraph paragraph;

    /**
     * Quote the paragraph.
     * @param paragraph paragraph
     */
    public Quote(Paragraph paragraph) {
        this.paragraph = paragraph;
    }

    @Override
    public void serialized(StringBuilder builder) {
        builder.append("> ");
        paragraph.serialized(builder);
    }
}
