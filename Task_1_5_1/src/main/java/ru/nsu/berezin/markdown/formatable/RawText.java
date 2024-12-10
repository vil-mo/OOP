package ru.nsu.berezin.markdown.formatable;

import java.util.List;

/**
 * An abstact class for any string of text.
 */
public abstract class RawText implements Formatable {

    private final static List<Character> specialCharacters = java.util.Arrays.asList('*', '\\');

    protected final String text;

    protected RawText(String text) {
        this.text = text;
    }

    /**
     * Puts `\` before any special character in the text. For example, if the
     * text is `*text*`, the transformed text will be `\*text\*`. This is done
     * to avoid problems with markdown parsers. Raw text should not contain
     * special characters.
     * 
     * Shouldn't be used directly, but rather by the subclasses. Use
     * {@link #serialized()} instead.
     */
    public String withoutSpecialCharactes() {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (specialCharacters.contains(c)) {
                builder.append('\\');
            }
            builder.append(c);
        }

        return builder.toString();
    }
}
