package ru.nsu.berezin.markdown.image;

import ru.nsu.berezin.markdown.formatable.Link;

public class LinkImage extends Image {
    Link link;

    public LinkImage(String address) {
        this.link = new Link("image", address);
    }

    @Override
    public void serialized(StringBuilder builder) {
        link.serialized(builder);
    }
}
