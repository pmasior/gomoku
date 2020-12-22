package edu.pm.gomoku.controllers;

import edu.pm.gomoku.core.Gomoku;
import javafx.fxml.FXML;

public class MainController {
    @FXML private BoardPaneController boardPaneController;
    @FXML private TopPaneController topPaneController;

    @FXML private void initialize() {
        Gomoku gomoku = new Gomoku();
        boardPaneController.setGomoku(gomoku);
        topPaneController.setGomoku(gomoku);
    }
}
