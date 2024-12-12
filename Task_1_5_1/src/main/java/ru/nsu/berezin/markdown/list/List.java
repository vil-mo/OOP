package ru.nsu.berezin.markdown.list;

import ru.nsu.berezin.markdown.Element;

/**
 * A markdown list.
 * Each element of the list is separated by a new line.
 * The begining of the element is indicated by a dash followed by a space.
 */
public class List extends Element {
    private final java.util.List<ListElement> elements;

    /**
     * Creates a list with specified elements.
     *
     * @param elements elements
     */
    public List(ListElement... elements) {
        this.elements = java.util.Arrays.asList(elements);
    }

    String serialized(int nesting) {
        StringBuilder builder = new StringBuilder();

        for (ListElement element : elements) {
            builder.append(element.serialized(nesting));
        }

        return builder.toString();
    }

    @Override
    public String serialized() {
        return serialized(0);
    }
}