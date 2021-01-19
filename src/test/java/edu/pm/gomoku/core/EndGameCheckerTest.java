package edu.pm.gomoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class EndGameCheckerTest {
    private static final int NUMBER_OF_FIELDS_IN_LINE = 15;
    private int[][] board;
    private EndGameChecker endGameChecker;

    @BeforeEach
    public void setUp() {
        board = new int[NUMBER_OF_FIELDS_IN_LINE][NUMBER_OF_FIELDS_IN_LINE];
        endGameChecker = new EndGameChecker(board);
    }

    @Test
    public void givenEmptyBoard_whenCheckWin_thenReturnFalse() {

        boolean result = endGameChecker.checkWin(0, 4);

        Assertions.assertFalse(result);
    }

    @Test
    public void givenFiveStonesHorizontally_whenCheckWin_thenReturnTrue() {
        board[0][0] = 1;
        board[0][1] = 1;
        board[0][2] = 1;
        board[0][3] = 1;
        board[0][4] = 1;

        boolean result = endGameChecker.checkWin(0, 4);

        Assertions.assertTrue(result);
    }

    @Test
    public void givenFiveStonesVertically_whenCheckWin_thenReturnTrue() {
        board[0][0] = 1;
        board[1][0] = 1;
        board[2][0] = 1;
        board[3][0] = 1;
        board[4][0] = 1;

        boolean result = endGameChecker.checkWin(4, 0);

        Assertions.assertTrue(result);
    }

    @Test
    public void givenFiveStonesDiagonally1_whenCheckWin_thenReturnTrue() {
        board[10][10] = 1;
        board[11][11] = 1;
        board[12][12] = 1;
        board[13][13] = 1;
        board[14][14] = 1;

        boolean result = endGameChecker.checkWin(14, 14);

        Assertions.assertTrue(result);
    }

    @Test
    public void givenFiveStonesDiagonally2_whenCheckWin_thenReturnTrue() {
        board[0][14] = 1;
        board[1][13] = 1;
        board[2][12] = 1;
        board[3][11] = 1;
        board[4][10] = 1;

        boolean result = endGameChecker.checkWin(4, 10);

        Assertions.assertTrue(result);
    }

    @Test
    public void givenSixStonesHorizontally_whenCheckWin_thenReturnFalse() {
        board[0][0] = 1;
        board[0][1] = 1;
        board[0][2] = 1;
        board[0][3] = 1;
        board[0][4] = 1;
        board[0][5] = 1;

        boolean result = endGameChecker.checkWin(0, 4);

        Assertions.assertFalse(result);
    }

    @Test
    public void givenSixStonesVertically_whenCheckWin_thenReturnFalse() {
        board[0][0] = 1;
        board[1][0] = 1;
        board[2][0] = 1;
        board[3][0] = 1;
        board[4][0] = 1;
        board[5][0] = 1;

        boolean result = endGameChecker.checkWin(4, 0);

        Assertions.assertFalse(result);
    }

    @Test
    public void givenSixStonesDiagonally1_whenCheckWin_thenReturnFalse() {
        board[9][9] = 1;
        board[10][10] = 1;
        board[11][11] = 1;
        board[12][12] = 1;
        board[13][13] = 1;
        board[14][14] = 1;

        boolean result = endGameChecker.checkWin(10, 10);

        Assertions.assertFalse(result);
    }

    @Test
    public void givenSixStonesDiagonally2_whenCheckWin_thenReturnFalse() {
        board[0][14] = 1;
        board[1][13] = 1;
        board[2][12] = 1;
        board[3][11] = 1;
        board[4][10] = 1;
        board[5][9] = 1;

        boolean result = endGameChecker.checkWin(4, 10);

        Assertions.assertFalse(result);
    }

    @Test
    public void givenFiveStonesWithBreakDiagonally1_whenCheckWin_thenReturnFalse() {
        board[0][0] = 1;
        board[1][1] = 1;
        board[2][2] = 1;
        board[3][3] = 1;
        board[5][5] = 1;

        boolean result = endGameChecker.checkWin(5, 5);

        Assertions.assertFalse(result);
    }

    @Test
    public void givenSevenStonesDiagonally1_whenCheckWin_thenReturnFalse() {
        board[0][0] = 1;
        board[1][1] = 1;
        board[2][2] = 1;
        board[3][3] = 1;
        board[5][5] = 1;
        board[4][4] = 1;
        board[6][6] = 1;

        boolean result = endGameChecker.checkWin(6, 6);

        Assertions.assertFalse(result);
    }
}
