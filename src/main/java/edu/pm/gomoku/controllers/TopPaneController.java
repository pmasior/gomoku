package edu.pm.gomoku.controllers;

import edu.pm.gomoku.core.Gomoku;
import edu.pm.gomoku.core.GomokuListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class TopPaneController {
    private static final String PLAYER_1_MOVE_TEXT = "Player 1 moves";
    private static final String PLAYER_2_MOVE_TEXT = "Player 2 moves";
    private static final String PLAYER_1_WINS_TEXT = "Player 1 wins";
    private static final String PLAYER_2_WINS_TEXT = "Player 2 wins";
    private static final String DRAW_TEXT = "Tie";
    private static final Color PLAYER_1_TEXT_COLOR = Color.web("#464444");
    private static final Color PLAYER_2_TEXT_COLOR = Color.web("#f3f3f3");
    private static final Color DRAW_TEXT_COLOR = Color.web("#986801");

    @FXML private AnchorPane topPane;
    @FXML private Button newGameButton;
    @FXML private Label player1WinsLabel;
    @FXML private Label player2WinsLabel;
    @FXML private Label gameStatusLabel;

    private Gomoku gomoku;

    public void setGomoku(Gomoku gomoku) {
        this.gomoku = gomoku;
        configureGomokuListener();
    }

    @FXML private void handleNewGameOnMouseClicked(MouseEvent event) {
        gomoku.startNewRound();
    }

    private void configureGomokuListener() {
        gomoku.addListener(new GomokuListener() {
            @Override
            public void newRoundStarted() {
                handleNewRoundStarted();
            }

            @Override
            public void winOccurred(int currentPlayerNumber, int numberOfWins1, int numberOfWins2) {
                handleWinOccurred(currentPlayerNumber, numberOfWins1, numberOfWins2);
            }

            @Override
            public void drawOccurred() {
                handleDrawOccurred();
            }

            @Override
            public void currentPlayerChanged(int newCurrentPlayer) {
                handleCurrentPlayerChanged(newCurrentPlayer);
            }
        });
    }

    private void handleNewRoundStarted() {
        setGameStatusLabel(PLAYER_1_MOVE_TEXT, PLAYER_1_TEXT_COLOR);
    }

    private void handleWinOccurred(int currentPlayerNumber, int numberOfWins1, int numberOfWins2) {
        if (currentPlayerNumber == 1) {
            setGameStatusLabel(PLAYER_1_WINS_TEXT, PLAYER_1_TEXT_COLOR);
        } else if (currentPlayerNumber == 2) {
            setGameStatusLabel(PLAYER_2_WINS_TEXT, PLAYER_2_TEXT_COLOR);
        }
        player1WinsLabel.setText(String.valueOf(numberOfWins1));
        player2WinsLabel.setText(String.valueOf(numberOfWins2));

    }

    private void handleDrawOccurred() {
        setGameStatusLabel(DRAW_TEXT, DRAW_TEXT_COLOR);
    }

    private void handleCurrentPlayerChanged(int newCurrentPlayer) {
        if (newCurrentPlayer == 1) {
            setGameStatusLabel(PLAYER_1_MOVE_TEXT, PLAYER_1_TEXT_COLOR);
        } else if (newCurrentPlayer == 2) {
            setGameStatusLabel(PLAYER_2_MOVE_TEXT, PLAYER_2_TEXT_COLOR);
        }
    }

    private void setGameStatusLabel(String text, Color color) {
        gameStatusLabel.setText(text);
        gameStatusLabel.setTextFill(color);
    }
}

