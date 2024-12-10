package ru.nsu.berezin.markdown.formatable;

/**
 * A string of text. When serialized, puts `\` before any special character in
 * the text. For example, if the text is `*text*`, the transformed text will be
 * `\*text\*`. This is done to avoid problems with markdown parsers. Raw text
 * should not contain special characters.
 */
public class Text extends RawText {

    /**
     * Creates a string containing the given text.
     * 
     * @param text text
     */
    public Text(String text) {
        super(text);
    }

    @Override
    public String serialized() {
        return withoutSpecialCharactes();
    }
}
