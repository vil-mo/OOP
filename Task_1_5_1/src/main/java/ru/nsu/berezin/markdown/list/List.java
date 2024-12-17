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

    void serialized(int nesting, StringBuilder builder) {
        for (ListElement element : elements) {
            element.serialized(nesting, builder);
        }
    }

    @Override
    public void serialized(StringBuilder builder) {
        serialized(0, builder);
    }
}