package ru.nsu.berezin;

public class GameState {

    public enum PlayerAction {
        DrawMore,
        Stop,
    }

    Dealer dealer = new Dealer();

    public GameState() {
        reset();
    }

    public final void reset() {
        dealer.reset();

        dealer.drawPlayer();
        dealer.drawPlayer();

        dealer.drawDealer();
        dealer.drawDealer();
    }

    

}