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

    public void serialized(int nesting, StringBuilder builder) {
        for (int i = 0; i < nesting; i++) {
            builder.append("    ");
        }
        builder.append("- ");
        paragraph.serialized(builder);
        builder.append("\n");

        if (subList.isPresent()) {
            subList.get().serialized(nesting + 1, builder);
        }
    }
}
