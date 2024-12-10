package ru.nsu.berezin.markdown;

import ru.nsu.berezin.markdown.formatable.Formatable;

/**
 * A format of a span. 
 */
public class Format extends Element {
    private final Formatable formatable;
    private final boolean italic = false;
    private final boolean bold = false;
    private final boolean underline = false;
    private final boolean strikethrough = false;

    /**
     * Creates a span without formatting.
     *
     * @param span span
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

    @Override
    public String serialized() {
        StringBuilder builder = new StringBuilder();
        String formatModifiers = formatModifiers();

        builder.append(formatModifiers);
        builder.append(formatable.serialized());
        builder.append(new StringBuilder(formatModifiers).reverse().toString());
        
        return builder.toString();
    }
}