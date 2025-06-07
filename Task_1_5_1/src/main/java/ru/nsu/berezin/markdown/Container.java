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

    /**
     * Adds an element to the container.
     *
     * @param element element
     */
    public void add(Element element) {
        this.elements.add(element);
    }

    @Override
    public void serialized(StringBuilder builder) {
        for (Element element : elements) {
            element.serialized(builder);
            builder.append("\n\n");
        }
    }
}