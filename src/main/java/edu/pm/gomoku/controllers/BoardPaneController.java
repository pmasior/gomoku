package edu.pm.gomoku.controllers;

import edu.pm.gomoku.Main;
import edu.pm.gomoku.core.Gomoku;
import edu.pm.gomoku.core.GomokuInterface;
import edu.pm.gomoku.core.GomokuListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class BoardPaneController {
    private static final Color WHITE_COLOR = Color.web("#ffffff");
    private static final int BOARD_MARGIN = 50;
    static final int GAP_BETWEEN_FIELDS = 50;  // TODO: make private
    private static final String BLACK_STONE_IMAGE_FILENAME = "pieceBlack_border11.png";
    private static final String WHITE_STONE_IMAGE_FILENAME = "pieceWhite_border11.png";

    @FXML private AnchorPane boardPane;
    @FXML private Canvas boardCanvas;
    private GraphicsContext graphicsContext;
    private Image blackStoneImage;
    private Image whiteStoneImage;

    private GomokuInterface gomoku;

    public void setGomoku(GomokuInterface gomoku) {
        this.gomoku = gomoku;
        configureGomokuListener();
        gomoku.startNewRound();
    }

    @FXML private void initialize() {
        setGraphicsContextForCanvas();
        clearBoardCanvas();
        drawBoardLines();
        loadStoneImages();
    }

    @FXML private void handleOnMouseClicked(MouseEvent event) {
        MoveCoordinatesConverter moveCoordinatesConverter = new MoveCoordinatesConverter(event.getX(), event.getY());
        if (moveCoordinatesConverter.checkIfClickedInField()) {
            gomoku.setNewStone(moveCoordinatesConverter.getRowInArray(), moveCoordinatesConverter.getColumnInArray());
        }
    }

    private void configureGomokuListener() {
        gomoku.addListener(new GomokuListener() {
            @Override
            public void newRoundStarted() {
                handleNewRoundStarted();
            }

            @Override
            public void newMovePerformed(int playerNumber, int row, int column) {
                handleNewMovePerformed(playerNumber, row, column);
            }
        });
    }

    private void handleNewRoundStarted() {
        clearBoardCanvas();
        drawBoardLines();
    }

    private void handleNewMovePerformed(int playerNumber, int row, int column) {
        MoveCoordinatesConverter moveCoordinatesConverter = new MoveCoordinatesConverter(row, column);
        moveCoordinatesConverter.calc();
        int xFieldPosition = moveCoordinatesConverter.getXFieldPosition();
        int yFieldPosition = moveCoordinatesConverter.getYFieldPosition();

        if (playerNumber == 1) {
            drawStone(blackStoneImage, xFieldPosition, yFieldPosition);
        } else if (playerNumber == 2) {
            drawStone(whiteStoneImage, xFieldPosition, yFieldPosition);
        }
    }

    private void setGraphicsContextForCanvas() {
        graphicsContext = boardCanvas.getGraphicsContext2D();
    }

    private void loadStoneImages() {
        blackStoneImage = loadImage(BLACK_STONE_IMAGE_FILENAME);
        whiteStoneImage = loadImage(WHITE_STONE_IMAGE_FILENAME);
    }

    private Image loadImage(String filename) {
        String location = Main.class.getResource(filename).toString();
        return new Image(location);
    }

    private void clearBoardCanvas() {
        graphicsContext.clearRect(0, 0, boardCanvas.getHeight(), boardCanvas.getWidth());
    }

    private void drawBoardLines() {
        graphicsContext.setStroke(WHITE_COLOR);
        graphicsContext.setLineWidth(2);
        int lineBegin = BOARD_MARGIN;
        int lineEnd = (int)boardCanvas.getHeight() - BOARD_MARGIN;
        for (int i = lineBegin; i <= lineEnd; i = i + GAP_BETWEEN_FIELDS) {
            graphicsContext.strokeLine(i, lineBegin, i, lineEnd);
            graphicsContext.strokeLine(lineBegin, i, lineEnd, i);
        }
    }

    private void drawStone(Image image, int xFieldPosition, int yFieldPosition) {
        graphicsContext.drawImage(image,
                xFieldPosition - (image.getWidth() / 2),
                yFieldPosition - (image.getHeight() / 2)
        );
    }
}
