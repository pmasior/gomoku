package edu.pm.gomoku.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GomokuTest {

    private Board board;
    private EndGameChecker endGameChecker;
    private Player player1;
    private Player player2;
    private Gomoku gomoku;
    private GomokuListener gomokuListener;

    @BeforeEach
    public void setUp() {
        board = Mockito.mock(Board.class);
        endGameChecker = Mockito.mock(EndGameChecker.class);
        player1 = Mockito.mock(Player.class);
        player2 = Mockito.mock(Player.class);
        gomoku = new Gomoku(board, endGameChecker, player1, player2);
    }

    public void setUpGomokuListenerMock() {
        gomokuListener = Mockito.mock(GomokuListener.class);
        gomoku.addListener(gomokuListener);
    }

    @Test
    public void givenNewGomoku_whenStartNewRound_thenCallClearBoard() {

        gomoku.startNewRound();

        Mockito.verify(board).clearBoard();
    }

    @Test
    public void givenNewGomoku_whenStartNewRound_thenNotifyNewRoundStarted() {
        setUpGomokuListenerMock();

        gomoku.startNewRound();

        Mockito.verify(gomokuListener).newRoundStarted();
    }

    @Test
    public void givenNewRound_whenSetNewStone_thenCallBoardSetMoveIfFieldIsEmpty() {
        setUpGomokuListenerMock();
        gomoku.startNewRound();

        gomoku.setNewStone(0, 1);

        Mockito.verify(board).setMoveIfFieldIsEmpty(Mockito.anyInt(), Mockito.eq(0), Mockito.eq(1));
    }

    @Test
    public void givenNewRound_whenSetNewStone_thenNotifyNewMovePerformed() {
        setUpGomokuListenerMock();
        gomoku.startNewRound();
        Mockito.when(board.setMoveIfFieldIsEmpty(Mockito.anyInt(), Mockito.eq(0), Mockito.eq(1)))
                .thenReturn(true);

        gomoku.setNewStone(0, 1);

        Mockito.verify(gomokuListener).newMovePerformed(Mockito.anyInt(), Mockito.eq(0), Mockito.eq(1));
    }

    @Test
    public void givenRound_whenNewMoveGivenInThisField_thenNotNotifyNewMovePerformed() {
        setUpGomokuListenerMock();
        gomoku.startNewRound();
        gomoku.setNewStone(0, 0);
        Mockito.when(board.setMoveIfFieldIsEmpty(Mockito.anyInt(), Mockito.eq(0), Mockito.eq(0)))
                .thenReturn(false);

        gomoku.setNewStone(0, 0);

        Mockito.verify(gomokuListener, Mockito.never())
                .newMovePerformed(Mockito.anyInt(), Mockito.eq(0), Mockito.eq(0));
    }

    @Test
    public void givenGomokuBeforeWinning_whenGiveCorrectWinningMove_thenNotifyWinOccurred() {
        setUpGomokuListenerMock();
        gomoku.startNewRound();
        Mockito.when(board.setMoveIfFieldIsEmpty(Mockito.anyInt(), Mockito.eq(0), Mockito.eq(0)))
                .thenReturn(true);
        Mockito.when(endGameChecker.checkWin(0,0))
                .thenReturn(true);

        gomoku.setNewStone(0, 0);

        Mockito.verify(gomokuListener)
                .winOccurred(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    public void givenGomokuBeforeWinning_whenPlayer1GiveCorrectWinningMove_thenIncrementPlayer1NumberOfWins() {
        setUpGomokuListenerMock();
        gomoku.startNewRound();
        Mockito.when(board.setMoveIfFieldIsEmpty(Mockito.anyInt(), Mockito.eq(0), Mockito.eq(0)))
                .thenReturn(true);
        Mockito.when(endGameChecker.checkWin(0,0))
                .thenReturn(true);

        gomoku.setNewStone(0, 0);

        Mockito.verify(player1).incrementNumberOfWins();
    }

    @Test
    public void givenGomokuBeforeWinning_whenPlayer2GiveCorrectWinningMove_thenIncrementPlayer2NumberOfWins() {
        setUpGomokuListenerMock();
        gomoku.startNewRound();
        Mockito.when(board.setMoveIfFieldIsEmpty(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(true);
        gomoku.setNewStone(0, 0);
        Mockito.when(endGameChecker.checkWin(0,1))
                .thenReturn(true);

        gomoku.setNewStone(0, 1);

        Mockito.verify(player2).incrementNumberOfWins();
    }

    @Test
    public void givenGomokuBeforeDraw_whenPlayerGiveLastCorrectMove_thenNotifyDrawOccurred() {
        setUpGomokuListenerMock();
        gomoku.startNewRound();
        Mockito.when(board.setMoveIfFieldIsEmpty(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(true);
        Mockito.when(endGameChecker.checkDraw())
                .thenReturn(true);

        gomoku.setNewStone(14, 14);

        Mockito.verify(gomokuListener).drawOccurred();
    }

    @Test
    public void givenNewRound_whenSetNewStone_thenNotifyCurrentPlayerChanged() {
        setUpGomokuListenerMock();
        gomoku.startNewRound();
        Mockito.when(board.setMoveIfFieldIsEmpty(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(true);

        gomoku.setNewStone(0, 0);

        Mockito.verify(gomokuListener).currentPlayerChanged(Mockito.anyInt());
    }
}
