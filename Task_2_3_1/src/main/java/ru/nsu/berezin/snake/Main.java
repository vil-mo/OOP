package ru.nsu.berezin.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main application.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Label l = new Label("Hello world");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main.
     *
     * @param args - Main args.
     */
    public static void main(String[] args) {
        launch();
    }
}
