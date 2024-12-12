package ru.nsu.berezin.markdown.list;

import java.util.Optional;

import ru.nsu.berezin.markdown.Paragraph;

/**
 * An element of the markdown list.
 */
public class ListElement {

    private final Paragraph paragraph;
    private final Optional<List> subList;

    /**
     * Creates a list element with a paragraph.
     *
     * @param paragraph A content of the element
     */
    public ListElement(Paragraph paragraph) {
        this.paragraph = paragraph;
        this.subList = Optional.empty();
    }

    /**
     * Creates a list element with a sublist.
     *
     * @param paragraph A content of the element
     * @param subList A sublist
     */
    public ListElement(Paragraph paragraph, List subList) {
        this.paragraph = paragraph;
        this.subList = Optional.of(subList);
    }

    public String serialized(int nesting) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < nesting; i++) {
            builder.append("    ");
        }
        builder.append("- ");
        builder.append(paragraph.serialized());
        builder.append("\n");

        if (subList.isPresent()) {
            builder.append(subList.get().serialized(nesting + 1));
        }

        return builder.toString();
    }
}
