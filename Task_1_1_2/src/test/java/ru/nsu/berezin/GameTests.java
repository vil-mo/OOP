package ru.nsu.berezin;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class GameTests {

    @Test
    void gameState() {
        GameState state = new GameState();
        Assertions.assertEquals(0, state.currentRound);
        Assertions.assertEquals(0, state.dealer.playerHand.size());
        Assertions.assertEquals(0, state.dealer.dealerHand.size());
    }

    @Test
    void game() {
        for (int i = 0; i < 100; i++) {
            TestInterface interface_ = new TestInterface();
            Game game = new Game(interface_);
            try {
                game.run();
            } catch (Exception e) {
                Assertions.assertEquals(1, interface_.gameStartedAmount);
                Assertions.assertEquals(1, interface_.roundStartedAmount);
                Assertions.assertEquals(1, interface_.firstHandDealtAmount);
                Assertions.assertEquals(1, interface_.playerTurnStartedAmount);
                assert interface_.requestPlayerActionAmount - interface_.cardIsDealtToPlayerAmount <= 1;
                Assertions.assertEquals(1, interface_.playerTurnEndedAmount);
                assert interface_.dealerTurnStartedAmount <= 1;
                assert interface_.dealerOpensCardAmount <= 1;
                assert interface_.dealerTurnEndedAmount <= 1;
                Assertions.assertEquals(1, interface_.roundEndedAmount);
                Assertions.assertEquals(
                        1,
                        interface_.playerWonAmount + interface_.dealerWonAmount + interface_.tieAmount
                );
            }
        }
    }

    @Test
    void readerWriter() {
        Reader reader = new StringReader("");
        StringWriter writer = new StringWriter();

        RuWriterReaderInterface interface_ = new RuWriterReaderInterface(writer, reader);

        GameState state = new GameState();
        interface_.setGameState(state);

        try {
            interface_.gameStarted();
            Assertions.assertEquals("Добро пожаловать в Блэкджек!\n", writer.toString());
            writer.getBuffer().setLength(0);

            interface_.roundStarted();
            Assertions.assertEquals("Раунд 0\n", writer.toString());
            writer.getBuffer().setLength(0);

            interface_.playerTurnStarted();
            Assertions.assertEquals("Ваш ход\n-------\n", writer.toString());
            writer.getBuffer().setLength(0);

            interface_.playerTurnEnded();
            Assertions.assertEquals("", writer.toString());
            writer.getBuffer().setLength(0);

            interface_.dealerTurnStarted();
            Assertions.assertEquals("Ход дилера\n-------\n", writer.toString());
            writer.getBuffer().setLength(0);
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
