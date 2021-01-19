package edu.pm.gomoku.core;

public class GomokuListener {
    public void newRoundStarted() {}
    public void newMovePerformed(int playerNumber, int row, int column) {}
    public void winOccurred(int currentPlayerNumber, int numberOfWins1, int numberOfWins2) {}
    public void drawOccurred() {}
    public void currentPlayerChanged(int newCurrentPlayer) {}
}
