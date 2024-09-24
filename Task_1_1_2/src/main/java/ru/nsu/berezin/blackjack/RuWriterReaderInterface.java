package ru.nsu.berezin.blackjack;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Scanner;

/**
 * Interface that implements game logic using `Writer` and `Reader` for
 * interaction with the player.
 */
public class RuWriterReaderInterface implements GameInterface {

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

    private void writeHand(List<Card> hand) throws IOException {
        boolean wasClosed = false;
        List<Integer> points = Dealer.handPoints(hand);

        writer.write("[");

        int i = 0;
        int pointsSum = 0;
        for (Card card : hand) {
            writer.write(card.toString());

            if (card.isClosed()) {
                wasClosed = true;
            } else {
                writer.write(" (" + points.get(i) + ")");
            }

            if (i < hand.size() - 1) {
                writer.write(", ");
            }

            pointsSum += points.get(i);
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
    public void setGameState(GameState state) {
        this.state = state;
    }

    /**
     * See {@link GameInterface#gameStarted()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void gameStarted() {
        try {
            writer.write("Добро пожаловать в Блэкджек!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#roundStarted()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void roundStarted() {
        try {
            writer.write("Раунд " + state.currentRound + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#firstHandDealt()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void firstHandDealt() {
        try {
            writer.write("Дилер раздал карты\n");
            writePlayerAndDealerHands();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#playerTurnStarted()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void playerTurnStarted() {
        try {
            writer.write("Ваш ход\n-------\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#requestPlayerAction()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public Game.Action requestPlayerAction() {
        try {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#cardIsDealtToPlayer(Card)}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void cardIsDealtToPlayer(Card dealt) {
        try {
            writer.write("Вы открыли карту " + dealt.toString() + "\n");
            writePlayerAndDealerHands();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void playerTurnEnded() {
    }

    /**
     * See {@link GameInterface#dealerTurnStarted()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void dealerTurnStarted() {
        try {
            writer.write("Ход дилера\n-------\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#dealerOpensCard(Card)}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void dealerOpensCard(Card opened) {
        try {
            writer.write("Дилер открывает закрытую карту " + opened.toString() + "\n");
            writePlayerAndDealerHands();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#cardIsDealtToDealer(Card)}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void cardIsDealtToDealer(Card dealt) {
        try {
            writer.write("Дилер открывает карту " + dealt.toString() + "\n");
            writePlayerAndDealerHands();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dealerTurnEnded() {
    }

    @Override
    public void roundEnded() {
    }

    /**
     * See {@link GameInterface#playerWon()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void playerWon() {
        try {
            writer.write("Вы выиграли раунд! ");
            writeScore();
            writer.write("\n\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#dealerWon()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void dealerWon() {
        try {
            writer.write("Дилер выиграл раунд! ");
            writeScore();
            writer.write("\n\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * See {@link GameInterface#tie()}
     * 
     * @throws RuntimeException - if IOException occurs
     */
    @Override
    public void tie() {
        try {
            writer.write("Ничья! ");
            writeScore();
            writer.write("\n\n\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
