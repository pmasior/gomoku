package edu.pm.gomoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class BoardTest {
    private static final int NUMBER_OF_FIELDS_IN_LINE = 15;
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void givenStoneInField_whenSetMoveIfFieldIsEmpty_thenReturnFalse() {
        board.setMoveIfFieldIsEmpty(1, 0, 0);

        boolean result = board.setMoveIfFieldIsEmpty(2, 0, 0);

        Assertions.assertFalse(result);
    }

    @Test
    public void givenEmptyBoard_whenSetMoveIfFieldIsEmpty_thenReturnTrue() {

        boolean result = board.setMoveIfFieldIsEmpty(1, 0, 0);

        Assertions.assertTrue(result);
    }

    @Test
    public void givenEmptyBoard_whenSetIncorrectMove_thenReturnFalse() {

        boolean result = board.setMoveIfFieldIsEmpty(1, -1, -1);

        Assertions.assertFalse(result);
    }

    @Test
    public void givenBoardWithMove_whenGetBoard_thenReturnArray() {
        board.setMoveIfFieldIsEmpty(1, 0, 1);

        int[][] result = board.getBoard();

        int[][] expectedResult = new int[NUMBER_OF_FIELDS_IN_LINE][NUMBER_OF_FIELDS_IN_LINE];
        expectedResult[0][1] = 1;
        Assertions.assertArrayEquals(expectedResult, result);
    }

    @Test
    public void givenEmptyBoard_whenGetBoard_thenReturnArray() {

        int[][] result = board.getBoard();

        int[][] expectedResult = new int[NUMBER_OF_FIELDS_IN_LINE][NUMBER_OF_FIELDS_IN_LINE];
        Assertions.assertArrayEquals(expectedResult, result);
    }
}
