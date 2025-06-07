package ru.nsu.berezin.markdown.formatable;

import ru.nsu.berezin.markdown.Serialized;

/**
 * A format of a span. 
 */
public class Format implements Serialized {
    private final Formatable formatable;
    private boolean italic = false;
    private boolean bold = false;
    private boolean underline = false;
    private boolean strikethrough = false;

    /**
     * Creates a span without formatting.
     *
     * @param formatable inner formatable
     */
    public Format(Formatable formatable) {
        this.formatable = formatable;
    }

    /**
     * Makes the span italic.
     *
     * @return this span
     */
    public Format italic() {
        this.italic = true;
        return this;
    }

    /**
     * Makes the span bold.
     *
     * @return this span
     */
    public Format bold() {
        this.bold = true;
        return this;
    }

    /**
     * Makes the span underlined.
     *
     * @return this span
     */
    public Format underline() {
        this.underline = true;
        return this;
    }

    /**
     * Makes the span strikethrough.
     *
     * @return this span
     */
    public Format strikethrough() {
        this.strikethrough = true;
        return this;
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
    public void serialized(StringBuilder builder) {
        String formatModifiers = formatModifiers();

        builder.append(formatModifiers);
        formatable.serialized(builder);
        builder.append(new StringBuilder(formatModifiers).reverse());
    }
}