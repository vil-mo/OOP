package ru.nsu.berezin.markdown.formatable;

/**
 * A line of code. Formatted with single backticks.
 */
public class Code extends RawText {
    /**
     * Creates a line of code containing the given text.
     * 
     * @param text text
     */
    public Code(String text) {
        super(text);
    }

    @Override
    public int length() {
        return text.length() + 2;
    }

    @Override
    public void serialized(StringBuilder builder) {
        builder.append('`');
        builder.append(text);
        builder.append('`');
    }
}