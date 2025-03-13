package ru.nsu.berezin.pizzeria.menu;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Menu of the pizzeria. It is only possible to place orders on the items in the menu.
 * @param items - list of menu items
 */
public record Menu(List<MenuItem> items) {
    /**
     * Reads the item list from the config json.
     * @param config json, representing the
     * @return menu
     * @throws IOException - if a low-level I/O problem occurs
     * @throws StreamReadException - if underlying input contains invalid content of type JsonParser supports (JSON for default case)
     * @throws DatabindException - if the input JSON structure does not match structure expected for result type (or has other mismatch issues)
     */
    public static Menu fromConfig(File config) throws IOException, StreamReadException, DatabindException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(config, Menu.class);
    }
}
