package ru.nsu.berezin.markdown.formatable;

import ru.nsu.berezin.markdown.Serialized;

/**
 * A format of a span. 
 */
public class Format implements Serialized {
    private final Formatable formatable;
    private final boolean italic = false;
    private final boolean bold = false;
    private final boolean underline = false;
    private final boolean strikethrough = false;

    /**
     * Creates a span without formatting.
     *
     * @param formatable inner formatable
     */
    public Format(Formatable formatable) {
        this.formatable = formatable;
    }

    /**
     * Returns the format modifiers of the span.
     *
     * @return format modifiers
     */
    public String formatModifiers() {
        StringBuilder builder = new StringBuilder();
        if (italic) {
            builder.append("*");
        }
        if (bold) {
            builder.append("**");
        }
        if (underline) {
            builder.append("__");
        }
        if (strikethrough) {
            builder.append("~~");
        }
        return builder.toString();
    }

    public int length() {
        return formatModifiers().length() * 2 + formatable.length();
    }

    @Override
    public void serialized(StringBuilder builder) {
        String formatModifiers = formatModifiers();

        builder.append(formatModifiers);
        formatable.serialized(builder);
        builder.append(new StringBuilder(formatModifiers).reverse());
        builder.append("\n");
    }
}