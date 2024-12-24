package ru.nsu.berezin.markdown.list;

import java.util.Optional;
import ru.nsu.berezin.markdown.Paragraph;

/**
 * An element of the markdown list.
 */
public class ListElement {

    private final Paragraph paragraph;
    private Optional<List> subList;

    /**
     * Creates a list element with a paragraph.
     *
     * @param paragraph A content of the element
     */
    ListElement(Paragraph paragraph) {
        this.paragraph = paragraph;
        this.subList = Optional.empty();
    }

    boolean hasSubList() {
        return subList.isPresent();
    }

    void setSubList(List subList) {
        this.subList = Optional.of(subList);
    }

    public void serialized(int nesting, StringBuilder builder) {
        paragraph.serialized(builder);
        builder.append("\n");

        if (subList.isPresent()) {
            subList.get().serialized(nesting + 1, builder);
        }
    }
}
