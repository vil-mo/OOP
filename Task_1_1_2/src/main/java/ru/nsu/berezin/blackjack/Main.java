package ru.nsu.berezin.blackjack;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

/**
 * Main class. It runs the game.
 */
public class Main {

    private Main() {
    }

    /**
     * Main method.
     *
     * @param args - not used
     */
    public static void main(String[] args) {

        Writer writer = new OutputStreamWriter(System.out);
        Reader reader = new InputStreamReader(System.in);

        RuWriterReaderInterface inteface = new RuWriterReaderInterface(writer, reader);
        Game game = new Game(inteface);

        try {
            game.run();
        } catch (RuntimeException e) {
            System.err.println("Ошибка IO");
            System.err.println(e.getCause().getMessage());
        }
    }
}
