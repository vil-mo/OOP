package ru.nsu.berezin.markdown.table;

import ru.nsu.berezin.markdown.Paragraph;

import java.util.Arrays;
import java.util.List;

/**
 * A builder for creating tables.
 */
public class TableBuilder {
    private final int columnCount;
    private final List<Paragraph[]> rows;
    private Table.Alignment[] alignments;

    TableBuilder(int columnCount) {
        this.columnCount = columnCount;
        this.rows = new java.util.ArrayList<>();
        alignments = new Table.Alignment[columnCount];
        Arrays.fill(alignments, Table.Alignment.LEFT);
    }

    /**
     * Finishes building the table.
     *
     * @return resulting table
     */
    public Table build() {
        return new Table(rows, alignments);
    }

    /**
     * Sets the alignments of the table.
     *
     * @param alignment alignments
     * @return this builder
     * @throws IllegalArgumentException if the number of alignments is incorrect
     */
    public TableBuilder withAlignments(Table.Alignment... alignment) throws IllegalArgumentException {
        if (alignment.length != columnCount) {
            throw new IllegalArgumentException("Incorrect number of alignments. Should be " + columnCount);
        }
        this.alignments = alignment;
        return this;
    }

    /**
     * Add a row of paragraphs to the table.
     *
     * @param elements paragraphs of the row
     * @return this builder
     * @throws IllegalArgumentException if the number of elements is incorrect
     */
    public TableBuilder addRow(Paragraph... elements) throws IllegalArgumentException {
        if (elements.length != columnCount) {
            throw new IllegalArgumentException("Incorrect number of elements. Should be " + columnCount);
        }

        rows.add(elements);

        return this;
    }
}
