package edu.pm.gomoku.core;

public interface GomokuInterface {
    void startNewRound();
    void setNewStone(int row, int column);
    void addListener(GomokuListener gomokuListener);
    void removeListener(GomokuListener gomokuListener);
}
