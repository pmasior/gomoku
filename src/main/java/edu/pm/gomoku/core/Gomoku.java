package edu.pm.gomoku.core;

import java.util.ArrayList;

public class Gomoku implements GomokuInterface {
    private final Player player1 = new Player(1);
    private final Player player2 = new Player(2);
    private Player currentPlayer;
    private boolean acceptNewMove = false;

    private Board board;
    private EndGameChecker endGameChecker;

    private final ArrayList<GomokuListener> listeners = new ArrayList<>();

    public void startNewRound() {
        board = new Board();
        endGameChecker = new EndGameChecker(board.getBoard());
        currentPlayer = player1;
        acceptNewMove = true;
        notifyNewRoundStarted();
    }

    public void setNewStone(int row, int column) {
        if (acceptNewMove) {
            acceptNewMove = false;
            if (board.setMoveIfFieldIsEmpty(currentPlayer.getPlayerNumber(), row, column)) {
                notifyNewMovePerformed(currentPlayer.getPlayerNumber(), row, column);
            }
            if (endGameChecker.checkWin(row, column)) {
                currentPlayer.incrementNumberOfWins();
                notifyWinOccurred(currentPlayer.getPlayerNumber(),
                        player1.getNumberOfWins(),
                        player2.getNumberOfWins());
            } else if (endGameChecker.checkDraw()) {
                notifyDrawOccurred();
            } else {
                changeNextPlayer();
                acceptNewMove = true;
                notifyCurrentPlayerChanged(currentPlayer.getPlayerNumber());
            }
        }
    }

    public void addListener(GomokuListener gomokuListener) {
        listeners.add(gomokuListener);
    }

    public void removeListener(GomokuListener gomokuListener) {
        listeners.remove(gomokuListener);
    }

    private void changeNextPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void notifyNewRoundStarted() {
        listeners.forEach(gomokuListener -> gomokuListener.newRoundStarted());
    }

    private void notifyNewMovePerformed(int playerNumber, int row, int column) {
        listeners.forEach(gomokuListener -> gomokuListener.newMovePerformed(playerNumber, row, column));
    }

    private void notifyWinOccurred(int currentPlayerNumber, int numberOfWins1, int numberOfWins2) {
        listeners.forEach(gomokuListener ->
                gomokuListener.winOccurred(currentPlayerNumber, numberOfWins1, numberOfWins2)
        );
    }

    private void notifyDrawOccurred() {
        listeners.forEach(gomokuListener -> gomokuListener.drawOccurred());
    }

    private void notifyCurrentPlayerChanged(int newCurrentPlayer) {
        listeners.forEach(gomokuListener -> gomokuListener.currentPlayerChanged(newCurrentPlayer));
    }

}