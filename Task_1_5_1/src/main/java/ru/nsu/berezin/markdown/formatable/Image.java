package ru.nsu.berezin.markdown.formatable;

import java.util.Optional;

/**
 * An image. Formatted with ![](address "title").
 */
public class Image implements Formatable {
    private final String address;
    private final Optional<Text> title;


    /**
     * Creates an image with the given address and title.
     *
     * @param address address
     * @param title title
     */
    public Image(String address, Text title) {
        this.address = address;
        this.title = Optional.of(title);
    }

    /**
     * Creates an image with the given address.
     *
     * @param address address
     */
    public Image(String address) {
        this.address = address;
        this.title = Optional.empty();
    }

    @Override
    public void serialized(StringBuilder builder) {
        builder.append("![");
        builder.append("](");
        builder.append(address);
        if (title.isPresent()) {
            builder.append(" \"");
            title.get().serialized(builder);
            builder.append('"');
        }
        builder.append(")");
    }
}