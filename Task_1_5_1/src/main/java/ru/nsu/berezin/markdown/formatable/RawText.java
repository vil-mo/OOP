package ru.nsu.berezin.markdown.formatable;

import java.util.List;

/**
 * An abstact class for any string of text.
 */
public abstract class RawText implements Formatable {

    private static final List<Character> specialCharacters = java.util.Arrays.asList('*', '\\', '`', '_', '~', '[', ']');

    protected final String text;

    protected RawText(String text) {
        this.text = text;
    }

    /**
     * Puts `\` before any special character in the text. For example, if the
     * text is `*text*`, the transformed text will be `\*text\*`. This is done
     * to avoid problems with markdown parser. Raw text should not contain
     * special characters. Shouldn't be used directly, but rather by the
     * subclasses. Use serialized instead.
     */
    public String withoutSpecialCharacters() {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == '\n') {
                builder.append("<br>");
            } else {
                if (specialCharacters.contains(c)) {
                    builder.append('\\');
                }

                builder.append(c);
            }
        }

        return builder.toString();
    }
}
