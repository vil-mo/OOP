package ru.nsu.berezin.markdown.list;

import java.util.Optional;
import ru.nsu.berezin.markdown.Paragraph;

/**
 * A builder for creating lists.
 */
public class ListBuilder {
    private final java.util.List<ListElement> rows;
    private List.ListType type;
    private Optional<ListBuilder> sub;

    ListBuilder() {
        this.rows = new java.util.ArrayList<>();
        this.type = List.ListType.Unordered;
    }

    /**
     * Finishes building the list.
     *
     * @return resulting list
     */
    public List build() {
        return new List(rows, type);
    }

    /**
     * Sets the type of the list.
     *
     * @param type type of the list
     * @return this builder
     */
    public ListBuilder withType(List.ListType type) {
        if (sub.isPresent()) {
            this.sub.get().withType(type);
        } else {
            this.type = type;
        }
        return this;
    }

    /**
     * Add a row.
     *
     * @param paragraph paragraph of the row
     * @return this builder
     */
    public ListBuilder addRow(Paragraph paragraph) {
        if (sub.isPresent()) {
            sub.get().addRow(paragraph);
        } else {
            rows.add(new ListElement(paragraph));
        }

        return this;
    }

    /**
     * Add a sublist to the list.
     *
     * @param sub sub list
     * @return this builder
     */
    public ListBuilder addSubList() {
        if (sub.isPresent()) {
            this.sub.get().addSubList();
        } else if (rows.isEmpty()) {
            throw new IllegalStateException("No rows to add sublist to");
        } else if (rows.getLast().hasSubList()) {
            throw new IllegalStateException("Last row already has a sublist");
        } else {
            this.sub = Optional.of(new ListBuilder());
        }
        return this;
    }

    /**
     * Ends building the sub list.
     *
     * @return resulting list
     */
    public ListBuilder endSubList() {
        if (!sub.isPresent()) {
            throw new IllegalStateException("No sublist to end");
        }
        innerEndSubList();
        return this;
    }

    void innerEndSubList() {
        if (sub.isPresent()) {
            sub.get().innerEndSubList();
        }
        ListBuilder subBuilder = sub.get();
        List list = subBuilder.build();
        rows.getLast().setSubList(list);
        sub = Optional.empty();
    }
}
