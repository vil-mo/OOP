package ru.nsu.berezin.markdown.table;

import ru.nsu.berezin.markdown.Paragraph;

import java.util.Arrays;
import java.util.List;

/**
 * A builder for creating tables.
 */
public class TableBuilder {
    private final int columnCount;
    private final List<TableElement[]> rows;
    private Table.Alignment[] currentAlignments;
    private final int[] columnWidths;

    TableBuilder(int columnCount) {
        this.columnCount = columnCount;
        this.rows = new java.util.ArrayList<>();
        this.columnWidths = new int[columnCount];
    }

    /**
     * Finishes building the table.
     *
     * @return resulting table
     */
    public Table build() {
        return new Table(rows, columnWidths);
    }

    public TableBuilder withAlignments(Table.Alignment... alignment) {
        if (alignment.length != columnCount) {
            throw new IllegalArgumentException("Incorrect number of alignments. Should be " + columnCount);
        }
        this.currentAlignments = alignment;
        return this;
    }
    /**
     * Add a row of paragraphs to the table.
     *
     * @param elements paragraphs of the row
     * @return this builder
     */
    public TableBuilder addRow(Paragraph... elements) {
        if (elements.length != columnCount) {
            throw new IllegalArgumentException("Incorrect number of elements. Should be " + columnCount);
        }

        TableElement[] row = new TableElement[columnCount];
        for (int i = 0; i < columnCount; i++) {
            row[i] = new TableElement(elements[i], currentAlignments[i]);

            int width = row[i].length();
            if (columnWidths[i] < width) {
                columnWidths[i] = width;
            }
        }

        return this;
    }
}
