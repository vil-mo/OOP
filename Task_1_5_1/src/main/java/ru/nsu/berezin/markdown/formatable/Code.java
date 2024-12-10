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
    public String serialized() {
        return "`" + text + "`";
    }
}