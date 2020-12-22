package edu.pm.gomoku.controllers;

import edu.pm.gomoku.Main;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class BoardPaneController {
    private static Color WHITE_COLOR = Color.web("#ffffff");
    private static int BOARD_MARGIN = 50;
    private static int FIELD_SIZE = 50;

    @FXML private AnchorPane boardPane;
    @FXML private Canvas boardCanvas;
    private GraphicsContext graphicsContext;
    private Image blackStoneImage;
    private Image whiteStoneImage;

    @FXML private void initialize() {
        setGraphicsContextForCanvas();
        drawBoardBackground();
        drawBoard();
        loadStoneImages();
    }

    @FXML private void handleOnMouseClicked(MouseEvent event) {
//        TODO: change
        drawStone(blackStoneImage, (int)event.getX(), (int)event.getY());
    }

    private void setGraphicsContextForCanvas() {
        graphicsContext = boardCanvas.getGraphicsContext2D();
    }

    private void loadStoneImages() {
        blackStoneImage = loadImage("pieceBlack_border11.png");
        whiteStoneImage = loadImage("pieceWhite_border11.png");
    }

    private Image loadImage(String filename) {
        String location = Main.class.getResource(filename).toString();
        return new Image(location);
    }

    private void drawBoardBackground() {
        graphicsContext.clearRect(0, 0, boardCanvas.getHeight(), boardCanvas.getWidth());
    }

    private void drawBoard() {
        graphicsContext.setStroke(WHITE_COLOR);
        graphicsContext.setLineWidth(2);
        int lineBegin = BOARD_MARGIN;
        int lineEnd = (int)boardCanvas.getHeight() - BOARD_MARGIN;
        for (int i = lineBegin; i <= lineEnd; i = i + FIELD_SIZE) {
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
