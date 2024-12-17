package ru.nsu.berezin.markdown.formatable;

public class Link extends RawText {
    String address;

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
