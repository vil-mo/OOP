package ru.nsu.berezin;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
            TestInterface testInterface = new TestInterface();
            Game game = new Game(testInterface);
            try {
                game.run();
            } catch (Exception e) {
                Assertions.assertEquals(1, testInterface.gameStartedAmount);
                Assertions.assertEquals(1, testInterface.roundStartedAmount);
                Assertions.assertEquals(1, testInterface.firstHandDealtAmount);
                Assertions.assertEquals(1, testInterface.playerTurnStartedAmount);
                assert 
                    testInterface.requestPlayerActionAmount - testInterface.cardIsDealtToPlayerAmount <= 1;
                Assertions.assertEquals(1, testInterface.playerTurnEndedAmount);
                assert testInterface.dealerTurnStartedAmount <= 1;
                assert testInterface.dealerOpensCardAmount <= 1;
                assert testInterface.dealerTurnEndedAmount <= 1;
                Assertions.assertEquals(1, testInterface.roundEndedAmount);
                Assertions.assertEquals(1,
                        testInterface.playerWonAmount + 
                        testInterface.dealerWonAmount + 
                        testInterface.tieAmount
                );
            }
        }
    }

    @Test
    void readerWriter() {
        Reader reader = new StringReader("1\n0\n");
        StringWriter writer = new StringWriter();

        RuWriterReaderInterface gameInterface = new RuWriterReaderInterface(writer, reader);

        GameState state = new GameState();
        gameInterface.setGameState(state);

        try {
            gameInterface.gameStarted();
            Assertions.assertEquals("Добро пожаловать в Блэкджек!\n", writer.toString());
            writer.getBuffer().setLength(0);

            gameInterface.roundStarted();
            Assertions.assertEquals("Раунд 0\n", writer.toString());
            writer.getBuffer().setLength(0);

            gameInterface.firstHandDealt();
            Assertions.assertEquals(
                "Дилер раздал карты\n\tВаши карты: [] => 0\n\tКарты дилера: [] => 0\n",
                writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.playerTurnStarted();
            Assertions.assertEquals("Ваш ход\n-------\n", writer.toString());
            writer.getBuffer().setLength(0);

            gameInterface.requestPlayerAction();
            Assertions.assertEquals(
                    "Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...\n", 
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.cardIsDealtToPlayer(new Card(Card.CardValue.Ace, Card.CardSuit.Clubs));
            Assertions.assertEquals(
                    "Вы открыли карту Туз Крести\n\tВаши карты: [] => 0\n\tКарты дилера: [] => 0\n",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.playerTurnEnded();
            Assertions.assertEquals(
                    "",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.dealerTurnStarted();
            Assertions.assertEquals(
                    "Ход дилера\n-------\n",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.dealerOpensCard(new Card(Card.CardValue.Ace, Card.CardSuit.Clubs));
            Assertions.assertEquals(
                    "Дилер открывает закрытую карту Туз Крести\n\tВаши карты: [] => 0\n\tКарты дилера: [] => 0\n",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.cardIsDealtToDealer(new Card(Card.CardValue.Ace, Card.CardSuit.Clubs));
            Assertions.assertEquals(
                    "Дилер открывает карту Туз Крести\n\tВаши карты: [] => 0\n\tКарты дилера: [] => 0\n",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.dealerTurnEnded();
            Assertions.assertEquals(
                    "",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.roundEnded();
            Assertions.assertEquals(
                    "",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.playerWon();
            Assertions.assertEquals(
                    "Вы выиграли раунд! Счёт: 0:0. Ничья по счёту!\n\n\n",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.dealerWon();
            Assertions.assertEquals(
                    "Дилер выиграл раунд! Счёт: 0:0. Ничья по счёту!\n\n\n",
                    writer.toString()
            );
            writer.getBuffer().setLength(0);

            gameInterface.tie();
            Assertions.assertEquals(
                    "Ничья! Счёт: 0:0. Ничья по счёту!\n\n\n",
                    writer.toString()
            );
        } catch (Exception e) {
            Assertions.fail();
        }
    }
}
