package ru.nsu.berezin.markdown.table;

import java.util.List;
import ru.nsu.berezin.markdown.Element;
import ru.nsu.berezin.markdown.Paragraph;

/**
 * A table. Formatted with pipes.
 */
public class Table extends Element {

    public enum Alignment {
        LEFT(":--"),
        RIGHT("--:"),
        CENTER(":-:");

        private final String serialized;

        Alignment(String serialized) {
            this.serialized = serialized;
        }
    }

    private final List<Paragraph[]> rows;
    private final Alignment[] alignments;

    Table(List<Paragraph[]> rows, Alignment[] alignments) {
        this.rows = rows;
        this.alignments = alignments;
    }

    /**
     * A builder for tables.
     * 
     * @param columnCount number of columns
     * @return builder
     */
    public static TableBuilder builder(int columnCount) {
        return new TableBuilder(columnCount);
    }

    @Override
    public void serialized(StringBuilder builder) {
        boolean printedAlign = false;

        for (Paragraph[] row : rows) {
            boolean first = true;
            for (Paragraph paragraph : row) {
                if (first) {
                    first = false;
                    builder.append("| ");
                } else {
                    builder.append(" | ");
                }

                paragraph.serialized(builder);
            }
            builder.append(" |\n");

            if (!printedAlign) {
                printedAlign = true;
                first = false;
                for (Alignment alignment : alignments) {
                    if (first) {
                        first = false;
                        builder.append("| ");
                    } else {
                        builder.append(" | ");
                    }

                    builder.append(alignment.serialized);

                }
                builder.append(" |\n");
            }
        }
    }
}
