package edu.pm.gomoku.core;

public class Board {
    private static final int NUMBER_OF_FIELDS_IN_LINE = 15;
    private final int[][] board = new int[NUMBER_OF_FIELDS_IN_LINE][NUMBER_OF_FIELDS_IN_LINE];

    public int[][] getBoard() {
        return board;
    }

    public boolean setMoveIfFieldIsEmpty(int playerNumber, int row, int column) {
        if (checkIfFieldIsEmpty(row, column)) {
            board[row][column] = playerNumber;
            return true;
        }
        return false;
    }

    private boolean checkIfFieldIsEmpty(int row, int column) {
        return board[row][column] == 0;
    }
}
