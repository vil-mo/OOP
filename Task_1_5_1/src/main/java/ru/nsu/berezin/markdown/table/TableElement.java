package ru.nsu.berezin.markdown.table;

import ru.nsu.berezin.markdown.Paragraph;
import ru.nsu.berezin.markdown.Serialized;

class TableElement {
    final Paragraph paragraph;
    final Table.Alignment alignment;

    public TableElement(Paragraph paragraph, Table.Alignment alignment) {
        this.paragraph = paragraph;
        this.alignment = alignment;
    }

    public void serialized(int width, StringBuilder builder) {
        int paragraphLength = paragraph.lenght();
        int padding = width - paragraphLength;

        if (alignment == Table.Alignment.LEFT) {
            paragraph.serialized(builder);
            builder.append(" ".repeat(Math.max(0, padding)));
        } else if (alignment == Table.Alignment.RIGHT) {
            builder.append(" ".repeat(Math.max(0, padding)));
            paragraph.serialized(builder);
        } else if (alignment == Table.Alignment.CENTER) {
            int leftPadding = padding / 2;
            int rightPadding = padding - leftPadding;
            builder.append(" ".repeat(Math.max(0, leftPadding)));
            paragraph.serialized(builder);
            builder.append(" ".repeat(Math.max(0, rightPadding)));
        }
    }
}
