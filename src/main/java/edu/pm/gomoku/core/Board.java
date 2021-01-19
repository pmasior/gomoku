package edu.pm.gomoku.core;

import java.util.Arrays;

public class Board {
    private static final int NUMBER_OF_FIELDS_IN_LINE = 15;
    private final int[][] board = new int[NUMBER_OF_FIELDS_IN_LINE][NUMBER_OF_FIELDS_IN_LINE];

    public int[][] getBoard() {
        return board;
    }

    /**
     * Clear all moves from the board
     */
    public void clearBoard() {
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }

    /**
     * Set move into board if field is empty
     *
     * @param playerNumber the number of the player making the move
     * @param row row number of the move on board
     * @param column column number of the move on board
     * @return true if move set into board, else false
     */
    public boolean setMoveIfFieldIsEmpty(int playerNumber, int row, int column) {
        if (checkIsValidField(row)
                && checkIsValidField(column)
                && checkIfFieldIsEmpty(row, column)) {
            board[row][column] = playerNumber;
            return true;
        }
        return false;
    }

    private boolean checkIfFieldIsEmpty(int row, int column) {
        return board[row][column] == 0;
    }

    private boolean checkIsValidField(int checkedField) {
        return checkedField >= 0 && checkedField < board.length;
    }
}
