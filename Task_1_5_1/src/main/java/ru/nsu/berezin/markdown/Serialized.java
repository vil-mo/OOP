package ru.nsu.berezin.markdown;

/**
 * A piece of data that can be serialized to a string.
 */
public interface Serialized {
    /**
     * Serializes the data to a string.
     * Appends it's data to the end of the builder.
     *
     * @param builder builder to append string data to
     */
    void serialized(StringBuilder builder);
}