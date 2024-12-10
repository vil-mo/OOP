package ru.nsu.berezin.markdown;

/**
 * A markdown list.
 * Each element of the list is separated by a new line and indented by four spaces.
 * The begining of the element is indicated by a dash followed by a space.
 */
public class List extends Element {
    java.util.List<Element> elements;

    public List(Element... elements) {
        this.elements = java.util.Arrays.asList(elements);
    }

    @Override
    public String serialized() {
        StringBuilder builder = new StringBuilder();

        for (Element element : elements) {
            builder.append("- ");
            builder.append(element.serialized());
            builder.append("\n");
        }

        return builder.toString();
    }
}