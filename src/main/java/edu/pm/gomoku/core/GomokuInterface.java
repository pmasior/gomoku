package edu.pm.gomoku.core;

public interface GomokuInterface {
    void startNewRound();
    void setNewStone(int row, int column);
    void addListener(GomokuListener gomokuListener);  // TODO: should be there or in dedicated interface?
    void removeListener(GomokuListener gomokuListener);  // TODO: should be there or in dedicated interface?
}
