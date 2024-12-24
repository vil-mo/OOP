package ru.nsu.berezin.markdown.list;

import ru.nsu.berezin.markdown.Element;

/**
 * A markdown list. Each element of the list is separated by a new line. The
 * begining of the element is indicated by a dash followed by a space.
 */
public class List extends Element {

    public enum ListType {
        Unordered,
        Ordered,
    }

    private final java.util.List<ListElement> elements;
    private final ListType type;

    /**
     * Creates a list with specified elements.
     *
     * @param elements elements
     */
    List(java.util.List<ListElement> elements, ListType type) {
        this.elements = elements;
        this.type = type;
    }

    /**
     * A builder for lists.
     */
    public static ListBuilder builder() {
        return new ListBuilder();
    }

    void serialized(int nesting, StringBuilder builder) {
        for (int i = 0; i < elements.size(); i++) {
            ListElement element = elements.get(i);
            for (int nest = 0; nest < nesting; nest++) {
                builder.append("    ");
            }
            switch (type) {
                case Unordered:
                    builder.append("* ");
                    break;
                case Ordered:
                    builder.append(i + 1);
                    builder.append(". ");
                    break;
            }

            element.serialized(nesting, builder);
        }
    }

    @Override
    public void serialized(StringBuilder builder) {
        serialized(0, builder);
    }
}
