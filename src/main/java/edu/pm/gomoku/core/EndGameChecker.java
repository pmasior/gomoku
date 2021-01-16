package edu.pm.gomoku.core;

public class EndGameChecker {
    private final int[][] board;

    public EndGameChecker(int[][] board) {
        this.board = board;
    }

    /**
     * Check if draw occurred on board (all fields are occupied)
     *
     * @return true if draw occurred, else false
     */
    public boolean checkDraw() {
        for (int[] row : board) {
            for (int field : row) {
                if (field == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if win occupied on board
     *
     * @param row row number of the last move on board
     * @param column column number of the last move on board
     * @return true if win occurred, else false
     */
    public boolean checkWin(int row, int column) {
        if (board[row][column] != 0) {
            return countSucceedingStonesHorizontally(row, column) == 5
                    || countSucceedingStonesVertically(row, column) == 5
                    || countSucceedingStonesDiagonally1(row, column) == 5
                    || countSucceedingStonesDiagonally2(row, column) == 5;
        }
        return false;
    }

    private int countSucceedingStonesHorizontally(int row, int column) {
        return 1
                + countSucceedingStones(row, column, 0, -1)
                + countSucceedingStones(row, column, 0, 1);
    }

    private int countSucceedingStonesVertically(int row, int column) {
        return 1
                + countSucceedingStones(row, column, -1, 0)
                + countSucceedingStones(row, column, 1, 0);
    }

    private int countSucceedingStonesDiagonally1(int row, int column) {
        return 1
                + countSucceedingStones(row, column, -1, -1)
                + countSucceedingStones(row, column, 1, 1);
    }

    private int countSucceedingStonesDiagonally2(int row, int column) {
        return 1
                + countSucceedingStones(row, column, 1, -1)
                + countSucceedingStones(row, column, -1, 1);
    }

    private int countSucceedingStones(int row, int column, int directionInRow, int directionInColumn) {
        int count = 0;
        int playerStoneInField = board[row][column];
        for (int i = 1; i <= 5; i++) {
            int checkedRow = row + directionInRow * i;
            int checkedColumn = column + directionInColumn * i;
            if (checkIsInvalidField(checkedRow)) {
                break;
            }
            if (checkIsInvalidField(checkedColumn)) {
                break;
            }
            if (board[checkedRow][checkedColumn] == playerStoneInField) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private boolean checkIsInvalidField(int checkedField) {
        return checkedField < 0 || checkedField >= board.length;
    }
}
