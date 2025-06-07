package ru.nsu.berezin.markdown.formatable;

/**
 * A link. Formatted with [text](address).
 */
public class Link extends RawText {
    private final String address;

    /**
     * Create a link.
     *
     * @param text text
     * @param address address
     */
    public Link(String text, String address) {
        super(text);
        this.address = address;
    }

    @Override
    public void serialized(StringBuilder builder) {
        builder.append("[");
        builder.append(text);
        builder.append("](");
        builder.append(address);
        builder.append(")");
    }
}
