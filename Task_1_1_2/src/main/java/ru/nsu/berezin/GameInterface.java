package ru.nsu.berezin;

/**
 * Game interface. It reacts to various game events and provides a way for the
 * player to interact with the game.
 * It shouldn't mutate the game state. Game state only passed so that interface
 * can use state to determine, what to show to the player.
 * 
 * 
 * @param <E> - type of exception that can be thrown by the interface
 */
public interface GameInterface<E extends Exception> {

    /**
     * Sets the game state. Every other callback will be called only when the
     * event happens in the state that is passed to this method.
     * 
     * 
     * @param state - state of the game. It should not be mutated by the interface.
     */
    public void setGameState(GameState state);

    /**
     * Called once when game starts.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void gameStarted() throws E;

    /**
     * Called when round starts. State is the state of the game at the start of
     * the round. `state.currentRound` is the number of the round that is
     * starting.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void roundStarted() throws E;

    /**
     * Called when the first hand of the round is dealt. You can access it using
     * `state.dealer`.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void firstHandDealt() throws E;

    /**
     * Called when player turn starts, right after `firstHandDealt`.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void playerTurnStarted() throws E;

    /**
     * Called every time player is tasked with selecting an Action.
     * 
     * 
     * @return - action to perform
     * @throws E - exception that can be thrown by the interface
     */
    public Game.Action requestPlayerAction() throws E;

    /**
     * Called every time the card is dealt to player. Happens if player chooses
     * `Action.DrawMore`.
     *
     *
     * @param dealt - card that was dealt to player
     * @throws E - exception that can be thrown by the interface
     */
    public void cardIsDealtToPlayer(Card dealt) throws E;

    /**
     * Called when player's turn ends. Happens if player chooses `Action.Stop`.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void playerTurnEnded() throws E;

    /**
     * Called when dealer's turn starts.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void dealerTurnStarted() throws E;

    /**
     * Called when dealer opens his closed card. Happens right after
     * `dealerTurnStarted`.
     * 
     * 
     * @param opened - card that was opened
     * @throws E - exception that can be thrown by the interface
     */
    public void dealerOpensCard(Card opened) throws E;

    /**
     * Called every time card is dealt to dealer.
     *
     *
     * @param dealt - card that was dealt to dealer
     * @throws E - exception that can be thrown by the interface
     */
    public void cardIsDealtToDealer(Card dealt) throws E;

    /**
     * Called when dealer's turn ends.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void dealerTurnEnded() throws E;

    /**
     * Called when round ends. `state.currentRound` is the number of the round
     * that is ending.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void roundEnded() throws E;

    /**
     * Called right after `roundEnded` if the player won.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void playerWon() throws E;

    /**
     * Called right after `roundEnded` if the dealer won.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void dealerWon() throws E;

    /**
     * Called right after `roundEnded` if the game ended in a tie.
     * 
     * 
     * @throws E - exception that can be thrown by the interface
     */
    public void tie() throws E;
}
