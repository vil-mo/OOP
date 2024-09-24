package ru.nsu.berezin;


class TestInterface implements GameInterface<Exception> {
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
    public void gameStarted() throws Exception {
        gameStartedAmount++;
    }

    @Override
    public void roundStarted() throws Exception {
        if (roundStartedAmount > 0) {
            throw new Exception();
        }
        roundStartedAmount++;
    }

    @Override
    public void firstHandDealt() throws Exception {
        firstHandDealtAmount++;
    }

    @Override
    public void playerTurnStarted() throws Exception {
        playerTurnStartedAmount++;
    }

    @Override
    public Game.Action requestPlayerAction() throws Exception {
        requestPlayerActionAmount++;
        return Game.Action.DrawMore;
    }

    @Override
    public void cardIsDealtToPlayer(Card dealt) throws Exception {
        cardIsDealtToPlayerAmount++;
    }

    @Override
    public void playerTurnEnded() throws Exception {
        playerTurnEndedAmount++;
    }

    @Override
    public void dealerTurnStarted() throws Exception {
        dealerTurnStartedAmount++;
    }

    @Override
    public void dealerOpensCard(Card opened) throws Exception {
        dealerOpensCardAmount++;
    }

    @Override
    public void cardIsDealtToDealer(Card dealt) throws Exception {
        cardIsDealtToDealerAmount++;
    }

    @Override
    public void dealerTurnEnded() throws Exception {
        dealerTurnEndedAmount++;
    }

    @Override
    public void roundEnded() throws Exception {
        roundEndedAmount++;
    }

    @Override
    public void playerWon() throws Exception {
        playerWonAmount++;
    }

    @Override
    public void dealerWon() throws Exception {
        dealerWonAmount++;
    }

    @Override
    public void tie() throws Exception {
        tieAmount++;
    }
}