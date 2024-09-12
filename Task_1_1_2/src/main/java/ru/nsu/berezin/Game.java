package ru.nsu.berezin;

/**
 * Game class. It's the entry point for the game.
 */
public final class Game {

    /**
     * Actions that player can take.
     */
    public enum PlayerAction {
        DrawMore,
        Stop,
    }

    GameInterface gameInterface;

    /**
     * Creates a new instance of the game. All events will call the methods of
     * the provided interface.
     *
     * 
     * @param gameInterface - provides a way to interact with the game
     */
    public Game(GameInterface gameInterface) {
        this.gameInterface = gameInterface;
    }

    public void run() {
        //TODO: Implement the game
    }
}
