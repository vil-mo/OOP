package ru.nsu.berezin.pizzeria;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import ru.nsu.berezin.pizzeria.menu.Menu;

import java.io.File;
import java.io.IOException;

/**
 * An abstraction for encapsulating all operations that can be done in pizzeria.
 */
public class PizzeriaFacade {
    Menu menu;

    public PizzeriaFacade(Menu menu) {
        this.menu = menu;
    }

    /**
     * Creates a new pizzeria facade from the config file.
     * @param menuConfig - json menu config
     * @return pizzeria facade
     * @throws IOException - if a low-level I/O problem occurs
     * @throws StreamReadException - if underlying input contains invalid content of type JsonParser supports (JSON for default case)
     * @throws DatabindException - if the input JSON structure does not match structure expected for result type (or has other mismatch issues)
     */
    public PizzeriaFacade fromConfigs(File menuConfig)
        throws IOException, StreamReadException, DatabindException
    {
        Menu menu = Menu.fromConfig(menuConfig);
        return new PizzeriaFacade(menu);
    }
}
