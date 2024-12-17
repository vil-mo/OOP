package ru.nsu.berezin.markdown.table;

import ru.nsu.berezin.markdown.Element;
import ru.nsu.berezin.markdown.Paragraph;

import java.util.List;

/**
 * A table.
 */
public class Table extends Element {
    public enum Alignment {
        LEFT,
        RIGHT,
        CENTER,
    }

    private final List<TableElement[]> rows;
    private final int[] columnWidths;

    private Table(List<TableElement[]> rows, int[] columnWidths) {
        this.rows = rows;
        this.columnWidths = columnWidths;
    }

    @Override
    public void serialized(StringBuilder builder) {
        for (TableElement[] row : rows) {
            boolean first = true;
            for (int i = 0; i < row.length; i++) {
                TableElement element = row[i];
                if (first) {
                    first = false;
                    builder.append("| ");
                }
                element.serialized(columnWidths[i], builder);
                builder.append(" | ");
            }
            builder.append(" |\n");
        }
    }
}
