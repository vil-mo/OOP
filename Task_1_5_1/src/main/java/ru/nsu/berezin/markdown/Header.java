package ru.nsu.berezin.markdown;

/**
 * A header. Each element of the header is separated by a new line.
 */
public class Header extends Element {
    private final int level;
    private final Paragraph text;

    /**
     * Creates a header with the given level and text.
     *
     * @param level  level
     * @param text   text
     */
    public Header(int level, Paragraph text) {
        this.level = level;
        this.text = text;
    }

    @Override
    public void serialized(StringBuilder builder) {
        builder.append("#".repeat(Math.max(1, level)));
        builder.append(" ");
        text.serialized(builder);
    }
}