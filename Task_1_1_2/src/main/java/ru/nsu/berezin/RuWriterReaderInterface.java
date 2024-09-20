package ru.nsu.berezin;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

/**
 * Interface that implements game logic using `Writer` and `Reader` for
 * interaction with the player.
 */
public class RuWriterReaderInterface implements GameInterface<IOException> {

    Writer writer;
    Scanner scanner;
    GameState state = null;

    /**
     * Creates a new interface that uses `writer` and `reader` for interaction
     * with the player.
     *
     * @param writer - used writer
     * @param reader - used reader
     */
    public RuWriterReaderInterface(Writer writer, Readable reader) {
        this.writer = writer;
        this.scanner = new Scanner(reader);
    }

    private void writeHand(Card[] hand) throws IOException {
        boolean wasClosed = false;
        int[] points = Dealer.handPoints(hand);

        writer.write("[");

        int i = 0;
        int pointsSum = 0;
        for (Card card : hand) {
            writer.write(card.toString());

            if (card.closed) {
                wasClosed = true;
            } else {
                writer.write(" (" + points[i] + ")");
            }

            if (i < hand.length - 1) {
                writer.write(", ");
            }

            pointsSum += points[i];
            i++;
        }
        writer.write("]");

        if (!wasClosed) {
            writer.write(" => " + pointsSum);
        }
    }

    private void writePlayerAndDealerHands() throws IOException {
        writer.write("\tВаши карты: ");
        writeHand(state.dealer.getPlayerHand());
        writer.write("\n\tКарты дилера: ");
        writeHand(state.dealer.getDealerHand());
        writer.write("\n");
    }

    @Override
    public void setGameState(GameState state) {
        this.state = state;
    }

    @Override
    public void gameStarted() throws IOException {
        writer.write("Добро пожаловать в Блэкджек!\n");
    }

    @Override
    public void roundStarted() throws IOException {
        writer.write("Раунд " + state.currentRound + "\n");
    }

    @Override
    public void firstHandDealt() throws IOException {
        writer.write("Дилер раздал карты\n");
        writePlayerAndDealerHands();
    }

    @Override
    public void playerTurnStarted() throws IOException {
        writer.write("Ваш ход\n-------\n");
    }

    @Override
    public Game.Action requestPlayerAction() throws IOException {
        writer.write("Введите \"1\", чтобы взять карту, и \"0\", чтобы остановиться...\n");

        while (true) {
            writer.flush();
            if (!scanner.hasNextLine()) {
                throw new IOException("Пользователь закрыл поток ввода");
            }
            String line = scanner.nextLine();

            switch (line) {
                case "1" -> {
                    return Game.Action.DrawMore;
                }
                case "0" -> {
                    return Game.Action.Stop;
                }
                default -> {
                    writer.write("Неверный ввод. Введите \"1\", чтобы взять карту, ");
                    writer.write("и \"0\", чтобы остановиться...\n");
                }
            }
        }
    }

    @Override
    public void cardIsDealtToPlayer(Card dealt) throws IOException {
        writer.write("Вы открыли карту " + dealt.toString() + "\n");
        writePlayerAndDealerHands();
    }

    @Override
    public void playerTurnEnded() throws IOException {
    }

    @Override
    public void dealerTurnStarted() throws IOException {
        writer.write("Ход дилера\n-------\n");
    }

    @Override
    public void dealerOpensCard(Card opened) throws IOException {
        writer.write("Дилер открывает закрытую карту " + opened.toString() + "\n");
        writePlayerAndDealerHands();
    }

    @Override
    public void cardIsDealtToDealer(Card dealt) throws IOException {
        writer.write("Дилер открывает карту " + dealt.toString() + "\n");
        writePlayerAndDealerHands();
    }

    @Override
    public void dealerTurnEnded() throws IOException {
    }

    @Override
    public void roundEnded() throws IOException {
    }

    private void writeScore() throws IOException {
        writer.write("Счёт: " + state.playerWins + ":" + state.dealerWins);
        if (state.playerWins == state.dealerWins) {
            writer.write(". Ничья по счёту!");
        } else if (state.playerWins > state.dealerWins) {
            writer.write(" в вашу пользу");
        } else {
            writer.write(" в пользу дилера");
        }
    }

    @Override
    public void playerWon() throws IOException {
        writer.write("Вы выиграли раунд! ");
        writeScore();
        writer.write("\n\n\n");
    }

    @Override
    public void dealerWon() throws IOException {
        writer.write("Дилер выиграл раунд! ");
        writeScore();
        writer.write("\n\n\n");
    }

    @Override
    public void tie() throws IOException {
        writer.write("Ничья! ");
        writeScore();
        writer.write("\n\n\n");
    }
}
