package edu.pm.gomoku.core;

import java.util.ArrayList;

public class Gomoku implements GomokuInterface {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private boolean acceptNewMove = false;

    private final Board board;
    private final EndGameChecker endGameChecker;

    private final ArrayList<GomokuListener> listeners = new ArrayList<>();

    public Gomoku(Board board, EndGameChecker endGameChecker, Player player1, Player player2) {
        this.board = board;
        this.endGameChecker = endGameChecker;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Starts new round in Gomoku
     */
    public void startNewRound() {
        board.clearBoard();
        currentPlayer = player1;
        acceptNewMove = true;
        notifyNewRoundStarted();
    }

    /**
     * Set move on board if possible and checks conditions for ending the round
     *
     * @param row row number of the move on board
     * @param column column number of the move on board
     */
    public void setNewStone(int row, int column) {
        if (!acceptNewMove) {
            return;
        }
        acceptNewMove = false;

        if (board.setMoveIfFieldIsEmpty(currentPlayer.getPlayerNumber(), row, column)) {
            notifyNewMovePerformed(currentPlayer.getPlayerNumber(), row, column);
        } else {
            acceptNewMove = true;
            return;
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

    /**
     * Add object to a list of objects which will be notified after the event occurs in Gomoku
     *
     * @param gomokuListener object which will be notified
     */
    public void addListener(GomokuListener gomokuListener) {
        listeners.add(gomokuListener);
    }

    /**
     * Remove object from a list of objects which will be notified after the event occurs in Gomoku
     *
     * @param gomokuListener object which will not be notified
     */
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