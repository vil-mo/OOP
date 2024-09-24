package ru.nsu.berezin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTests {
    @Test
    void gameState() {
        GameState state = new GameState();
        assertEquals(0, state.currentRound);
        assertEquals(0, state.dealer.playerHand.size());
        assertEquals(0, state.dealer.dealerHand.size());
    }

    @Test
    void game() {
        TestInterface interface_ = new TestInterface();
        Game game = new Game(interface_);
        try {
            game.run();
        } catch (Exception e) {
            assertEquals(interface_.gameStartedAmount, 1);
            assertEquals(interface_.roundStartedAmount, 1);
            assertEquals(interface_.firstHandDealtAmount, 1);
            assertEquals(interface_.playerTurnStartedAmount, 1);
            assert interface_.requestPlayerActionAmount - interface_.cardIsDealtToPlayerAmount <= 1;
            assertEquals(interface_.playerTurnEndedAmount, 1);
            assert interface_.dealerTurnStartedAmount <= 1;
            assert interface_.dealerOpensCardAmount <= 1;
            assert interface_.dealerTurnEndedAmount <= 1;
            assertEquals(interface_.roundEndedAmount, 1);
            assertEquals(interface_.playerWonAmount + interface_.dealerWonAmount + interface_.tieAmount, 1);
        }
    }
}