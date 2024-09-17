package ru.nsu.berezin;

import java.util.stream.IntStream;

/**
 * Game class. It's the entry point for the game.
 *
 * @param <E> - type of exception that can be thrown by the interface
 */
public final class Game<E extends Exception> {

    /**
     * Actions that can be taken in a turn.
     */
    public enum Action {
        /**
         * Draw more cards.
         */
        DrawMore,
        /**
         * Stop your turn and pass the turn to the dealer 
         * (or end the game if it's a dealer) who stops.
         */
        Stop,
    }

    GameInterface<E> gameInterface;

    /**
     * Creates a new instance of the game. All events will call the methods of
     * the provided interface.
     *
     * @param gameInterface - provides a way to interact with the game
     */
    public Game(GameInterface<E> gameInterface) {
        this.gameInterface = gameInterface;
    }

    /**
     * Lose - hand has more than 21 points. Max - hand has exactly 21 points.
     * Continue - hand has less than 21 points.
     */
    private enum CheckedHand {
        Lose,
        Max,
        Continue,
    }

    private CheckedHand checkHand(Card[] hand) {
        int points = IntStream.of(Dealer.handPoints(hand)).sum();

        if (points == 21) {
            return CheckedHand.Lose;
        } else if (points > 21) {
            return CheckedHand.Max;
        } else {
            return CheckedHand.Continue;
        }
    }

    private Action requestDealerAction(Card[] hand) {
        int points = IntStream.of(Dealer.handPoints(hand)).sum();

        if (points < 17) {
            return Action.DrawMore;
        } else {
            return Action.Stop;
        }
    }

    private enum GameResult {
        PlayerWon,
        DealerWon,
        Tie,
    }

    private GameResult compareHands(Card[] player, Card[] dealer) {
        int playerPoints = IntStream.of(Dealer.handPoints(player)).sum();
        int dealerPoints = IntStream.of(Dealer.handPoints(dealer)).sum();

        if (playerPoints > 21) {
            if (dealerPoints > 21) {
                return GameResult.Tie;
            } else {
                return GameResult.DealerWon;
            }
        } else if (dealerPoints > 21) {
            return GameResult.PlayerWon;
        }

        if (playerPoints > dealerPoints) {
            return GameResult.PlayerWon;
        } else if (playerPoints < dealerPoints) {
            return GameResult.DealerWon;
        } else {
            return GameResult.Tie;
        }
    }

    /**
     * Runs the game in an infinite loop.
     *
     * @throws E - exception that can be thrown by the interface
     */
    public void run() throws E {
        GameState state = new GameState();
        gameInterface.setGameState(state);
        gameInterface.gameStarted();

        while (true) {
            state.dealer.reset();
            state.currentRound++;
            gameInterface.roundStarted();

            state.dealer.dealFirstHand();
            gameInterface.firstHandDealt();

            gameInterface.playerTurnStarted();

            CheckedHand checkedPlayerHand = checkHand(state.dealer.getPlayerHand());

            player_loop:
            while (checkedPlayerHand == CheckedHand.Continue) {
                Action action = gameInterface.requestPlayerAction();
                switch (action) {
                    case DrawMore -> {
                        Card drawn = state.dealer.drawPlayer();
                        gameInterface.cardIsDealtToPlayer(drawn);
                    }
                    case Stop -> {
                        break player_loop;
                    }
                }

                checkedPlayerHand = checkHand(state.dealer.getPlayerHand());
            }

            gameInterface.playerTurnEnded();

            if (checkedPlayerHand != CheckedHand.Lose) {
                gameInterface.dealerTurnStarted();
                Card opened = state.dealer.openDealerCard();
                gameInterface.dealerOpensCard(opened);

                CheckedHand checkedDealerHand = checkHand(state.dealer.getDealerHand());

                dealer_loop:
                while (checkedDealerHand == CheckedHand.Continue) {
                    Action action = requestDealerAction(state.dealer.getDealerHand());
                    switch (action) {
                        case DrawMore -> {
                            Card drawn = state.dealer.drawDealer();
                            gameInterface.cardIsDealtToDealer(drawn);
                        }
                        case Stop -> {
                            break dealer_loop;
                        }
                    }

                    checkedDealerHand = checkHand(state.dealer.getDealerHand());
                }

                gameInterface.dealerTurnEnded();
            }

            gameInterface.roundEnded();
            GameResult result = compareHands(
                    state.dealer.getPlayerHand(),
                    state.dealer.getDealerHand()
            );

            switch (result) {
                case PlayerWon -> {
                    state.playerWins++;
                    gameInterface.playerWon();
                }
                case DealerWon -> {
                    state.dealerWins++;
                    gameInterface.dealerWon();
                }
                case Tie -> {
                    gameInterface.tie();
                }
            }
        }
    }
}
