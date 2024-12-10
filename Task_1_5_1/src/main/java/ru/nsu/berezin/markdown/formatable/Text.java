package ru.nsu.berezin.markdown.formatable;

import java.util.List;

/**
 * A span of unformatted text.
 * When serialized, the text will be transformed to remove occurrences of specail symbols ({@link #transformSpecialSymbols()}).
 */
public class Text implements Formatable {
    public static List<Character> specialCharacters = java.util.Arrays.asList('*', '\\');

    private final String text;
    
    /**
     * Creates a span containing the given text.
     *
     * @param text text
     */
    public Text(String text) {
        this.text = text;
    }

    /**
     * Puts `\` before any special character in the text.
     * For example, if the text is `*text*`, the transformed text will be `\*text\*`.
     * This is done to avoid problems with markdown parsers. Raw text should not contain special characters.
     */
    @Override
    public String serialized() {
        StringBuilder builder = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (c == '*' || c =='\\') {
                builder.append('\\');
            }
            builder.append(c);
        }

        return builder.toString();
    }
}