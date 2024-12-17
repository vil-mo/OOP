package ru.nsu.berezin.markdown.image;

import ru.nsu.berezin.markdown.formatable.Link;

/**
 * A link to an image.
 */
public class LinkImage extends Image {
    private final Link link;

    /**
     * Image with an address.
     *
     * @param address address of the image
     */
    public LinkImage(String address) {
        this.link = new Link("image", address);
    }

    @Override
    public void serialized(StringBuilder builder) {
        link.serialized(builder);
    }
}
