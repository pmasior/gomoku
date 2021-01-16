package edu.pm.gomoku.controllers;

import edu.pm.gomoku.core.*;
import javafx.fxml.FXML;

public class MainController {
    @FXML private BoardPaneController boardPaneController;
    @FXML private TopPaneController topPaneController;

    @FXML private void initialize() {
        Board board = new Board();
        EndGameChecker endGameChecker = new EndGameChecker(board.getBoard());
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        GomokuInterface gomoku = new Gomoku(board, endGameChecker, player1, player2);
        boardPaneController.setGomoku(gomoku);
        topPaneController.setGomoku(gomoku);
        gomoku.startNewRound();
    }
}
