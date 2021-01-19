package edu.pm.gomoku.core;

public class Player {
    private final int playerNumber;
    private int numberOfWins;

    public Player(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void incrementNumberOfWins() {
        numberOfWins++;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }
}
