package ru.nsu.berezin.markdown;

import ru.nsu.berezin.markdown.formatable.Format;


public class Header extends Element {
    private final int level;
    private final Format text;

    /**
     * Creates a header with the given level and text.
     *
     * @param level  level
     * @param text   text
     */
    public Header(int level, Format text) {
        this.level = level;
        this.text = text;
    }

    @Override
    public String serialized() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < level; i++) {
            builder.append("#");
        }
        builder.append(" ");
        builder.append(text.serialized());
        builder.append("\n");
        return builder.toString();
    }
}