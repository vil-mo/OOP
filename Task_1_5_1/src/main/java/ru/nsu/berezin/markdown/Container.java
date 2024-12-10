package ru.nsu.berezin.markdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A container that contains multiple elements.
 */
public class Container extends Element {
    private final List<Element> elements = new ArrayList<>();

    /**
     * Creates an empty container.
     */
    public Container() {
    }

    /**
     * Creates a container with the given elements.
     *
     * @param elements elements
     */
    public Container(Element... elements) {
        this.elements.addAll(Arrays.asList(elements));
    }

    @Override
    public String serialized() {
        StringBuilder builder = new StringBuilder();
        for (Element element : elements) {
            builder.append(element.serialized());
        }
        return builder.toString();
    }
}