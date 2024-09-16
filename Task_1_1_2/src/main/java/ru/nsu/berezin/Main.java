package ru.nsu.berezin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Main {

    public static void main(String[] args) {

        Writer writer = new OutputStreamWriter(System.out);
        Reader reader = new InputStreamReader(System.in);

        RuWriterReaderInterface inteface = new RuWriterReaderInterface(writer, reader);
        Game<IOException> game = new Game(inteface);

        try {
            game.run();
        } catch (IOException e) {
            System.err.println("Ошибка IO");
            System.err.println(e.getMessage());
        }
    }
}
