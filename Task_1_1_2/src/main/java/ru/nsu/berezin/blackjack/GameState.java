package ru.nsu.berezin.blackjack;

/**
 * Stores the state of the game. It's passed to the game interface when various
 * events happen.
 */
public class GameState {

    /**
     * Current round. Is incremented every time before round starts.
     */
    public int currentRound = 0;

    /**
     * Amount of rounds the player won.
     */
    public int playerWins = 0;
    /**
     * Amount of rounds the dealer won.
     */
    public int dealerWins = 0;

    /**
     * The dealer. Can you can use it to get hands of both parties.
     */
    public Dealer dealer = new Dealer();

    /**
     * Creates a new state.
     */
    public GameState() {
    }
}
