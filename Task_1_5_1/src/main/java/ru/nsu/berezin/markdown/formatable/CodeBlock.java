package ru.nsu.berezin.markdown.formatable;

import java.util.Optional;

/**
 * A block of code. Formatted with three single backticks and optional language identifier.
 */
public class CodeBlock extends RawText {
    public enum Language {
        C("c"),
        CPP("cpp"),
        JAVA("java"),
        PYTHON("python"),
        RUBY("ruby"),
        PHP("php"),
        HTML("html"),
        CSS("css"),
        JS("js");

        private final String identifier;

        Language(String identifier) {
            this.identifier = identifier;
        }

        public String identifier() {
            return identifier;
        }
    }

    private final Optional<Language> language;

    /**
     * Creates a line of code containing the given text.
     * 
     * @param text text
     */
    public CodeBlock(String text) {
        super(text);
        this.language = Optional.empty();
    }

    /**
     * Creates a line of code containing the given text and language identifier.
     * 
     * @param text text
     * @param language language identifier
     */
    public CodeBlock(String text, Language language) {
        super(text);
        this.language = Optional.of(language);
    }

    @Override
    public void serialized(StringBuilder builder) {
        if (language.isEmpty()) {
            builder.append("```");
            builder.append(text);
            builder.append("```");
        } else {
            builder.append("```");
            builder.append(language.get().identifier());
            builder.append('\n');
            builder.append(text);
            builder.append("```");
        }
    }
}