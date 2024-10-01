package ru.nsu.berezin.blackjack;

class TestInterface implements GameInterface {

    int gameStartedAmount = 0;
    int roundStartedAmount = 0;
    int firstHandDealtAmount = 0;
    int playerTurnStartedAmount = 0;
    int requestPlayerActionAmount = 0;
    int cardIsDealtToPlayerAmount = 0;
    int playerTurnEndedAmount = 0;
    int dealerTurnStartedAmount = 0;
    int dealerOpensCardAmount = 0;
    int cardIsDealtToDealerAmount = 0;
    int dealerTurnEndedAmount = 0;
    int roundEndedAmount = 0;
    int playerWonAmount = 0;
    int dealerWonAmount = 0;
    int tieAmount = 0;

    @Override
    public void setGameState(GameState state) {
    }

    @Override
    public void gameStarted() {
        gameStartedAmount++;
    }

    @Override
    public void roundStarted() {
        if (roundStartedAmount > 0) {
            throw new RuntimeException();
        }
        roundStartedAmount++;
    }

    @Override
    public void firstHandDealt() {
        firstHandDealtAmount++;
    }

    @Override
    public void playerTurnStarted() {
        playerTurnStartedAmount++;
    }

    @Override
    public Game.Action requestPlayerAction() {
        requestPlayerActionAmount++;
        if (requestPlayerActionAmount < 2) {
            return Game.Action.DrawMore;
        } else {
            return Game.Action.Stop;
        }
    }

    @Override
    public void cardIsDealtToPlayer(Card dealt) {
        cardIsDealtToPlayerAmount++;
    }

    @Override
    public void playerTurnEnded() {
        playerTurnEndedAmount++;
    }

    @Override
    public void dealerTurnStarted() {
        dealerTurnStartedAmount++;
    }

    @Override
    public void dealerOpensCard(Card opened) {
        dealerOpensCardAmount++;
    }

    @Override
    public void cardIsDealtToDealer(Card dealt) {
        cardIsDealtToDealerAmount++;
    }

    @Override
    public void dealerTurnEnded() {
        dealerTurnEndedAmount++;
    }

    @Override
    public void roundEnded() {
        roundEndedAmount++;
    }

    @Override
    public void playerWon() {
        playerWonAmount++;
    }

    @Override
    public void dealerWon() {
        dealerWonAmount++;
    }

    @Override
    public void tie() {
        tieAmount++;
    }
}
