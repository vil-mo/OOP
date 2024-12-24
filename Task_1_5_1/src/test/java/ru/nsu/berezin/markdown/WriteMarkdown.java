package ru.nsu.berezin.markdown;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.jupiter.api.Test;

import ru.nsu.berezin.markdown.formatable.Format;
import ru.nsu.berezin.markdown.formatable.Image;
import ru.nsu.berezin.markdown.formatable.Link;
import ru.nsu.berezin.markdown.formatable.Text;
import ru.nsu.berezin.markdown.list.List;
import ru.nsu.berezin.markdown.list.List.ListType;
import ru.nsu.berezin.markdown.table.Table;
import ru.nsu.berezin.markdown.table.Table.Alignment;

class WriteMarkdown {

    Element getElement() {
        Container container = new Container();
        container.add(new Paragraph("Hello, world!"));
        container.add(new Header(1, new Paragraph("Header 1!")));
        container.add(new Header(4, new Paragraph("Header 4!")));

        container.add(new Paragraph(new Format(new Text("Italic!")).italic()));
        container.add(new Paragraph(new Format(new Text("Bold!")).bold()));
        container.add(new Paragraph(new Format(new Text("Underline!")).underline()));
        container.add(new Paragraph(new Format(new Text("Strikethrough!")).strikethrough()));
        container.add(new Paragraph(new Format(new Text("Italic and bold!")).italic().bold()));
        container.add(new Paragraph(
            new Format(new Text("Different!")).italic(), 
            new Format(new Text("spans!")).bold())
        );

        container.add(List.builder()
            .addRow(new Paragraph("List item 1"))
            .addRow(new Paragraph("List item 2"))
            .addRow(new Paragraph("List item 3"))
            .addSubList()
            .addRow(new Paragraph("Sublist item 1"))
            .addRow(new Paragraph("Sublist item 2"))
            .withType(ListType.Ordered)
            .build()
        );

        container.add(new Paragraph(new Format(new Image("/image/sample.webp", new Text("Sample image")))));

        container.add(new Paragraph(
            new Format(new Text("This is a link: ")),
            new Format(new Link("address", "https://markdownlivepreview.com/"))
        ));

        container.add(
            Table.builder(3)
                .withAlignments(Alignment.CENTER, Alignment.RIGHT, Alignment.LEFT)
                .addRow(new Paragraph("Header 1"), new Paragraph("Header 2"), new Paragraph("Header 3"))
                .addRow(new Paragraph("Cell 1"), new Paragraph("Cell 2"), new Paragraph("Cell 3"))
                .addRow(new Paragraph("Cell 4"), new Paragraph("Cell 5"), new Paragraph("Cell 6"))
                .build()
            );

        return container;
    }

    @Test
    public void checkReadFromFile() throws IOException {
        File file = new File("./test.md");
        PrintWriter writer = new PrintWriter(file);
        StringBuilder builder = new StringBuilder();

        Element element = this.getElement();
        element.serialized(builder);
        writer.write(builder.toString());
        writer.flush();
    }
}
