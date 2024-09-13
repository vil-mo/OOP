package ru.nsu.berezin;

/**
 * Game interface. It reacts to various game events and provides a way for the
 * player to interact with the game.
 *
 * It shouldn't mutate the game state. Game state only passed so that interface
 * can use state to determine, what to show to the player.
 */
public interface GameInterface {

    /**
     * Sets the game state. Every other callback will be called only when the
     * event happens in the state that is passed to this method.
     */
    public void setGameState(GameState state);

    /**
     * Called once when game starts.
     */
    public void gameStarted();

    /**
     * Called when round starts. State is the state of the game at the start of
     * the round. `state.currentRound` is the number of the round that is
     * starting.
     */
    public void roundStarted();

    /**
     * Called when the first hand of the round is dealt. You can access it using
     * `state.dealer`.
     */
    public void firstHandDealt();

    /**
     * Called when player turn starts, right after `firstHandDealt`.
     */
    public void playerTurnStarted();

    /**
     * Called every time player is tasked with selecting an Action.
     */
    public Game.Action requestPlayerAction();

    /**
     * Called every time the card is dealt to player. Happens if player chooses
     * `Action.DrawMore`.
     *
     *
     * @param dealt - card that was dealt to player
     */
    public void cardIsDealtToPlayer(Card dealt);

    /**
     * Called when player's turn ends. Happens if player chooses `Action.Stop`.
     */
    public void playerTurnEnded();

    /**
     * Called when dealer's turn starts.
     */
    public void dealerTurnStarted();

    /**
     * Called when dealer opens his closed card. Happens right after
     * `dealerTurnStarted`.
     */
    public void dealerOpensCard(Card opened);

    /**
     * Called every time card is dealt to dealer.
     *
     *
     * @param dealt - card that was dealt to dealer
     */
    public void cardIsDealtToDealer(Card dealt);

    /**
     * Called when dealer's turn ends.
     */
    public void dealerTurnEnded();

    /**
     * Called when round ends. `state.currentRound` is the number of the round
     * that is ending.
     */
    public void roundEnded();

    /**
     * Called right after `roundEnded` if the player won.
     */
    public void playerWon();

    /**
     * Called right after `roundEnded` if the dealer won.
     */
    public void dealerWon();

    /**
     * Called right after `roundEnded` if the game ended in a tie.
     */
    public void tie();
}
