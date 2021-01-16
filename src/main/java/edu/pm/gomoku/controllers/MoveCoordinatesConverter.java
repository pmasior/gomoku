package edu.pm.gomoku.controllers;

public class MoveCoordinatesConverter {
    private static final int STONE_RADIUS = 23;

    private double xMousePosition = -1;
    private double yMousePosition = -1;
    private int xFieldPosition = -1;
    private int yFieldPosition = -1;
    private int rowInArray = -1;
    private int columnInArray = -1;

    public MoveCoordinatesConverter(double xMousePosition, double yMousePosition) {
        this.xMousePosition = xMousePosition;
        this.yMousePosition = yMousePosition;
    }

    public MoveCoordinatesConverter(int rowInArray, int columnInArray) {
        this.rowInArray = rowInArray;
        this.columnInArray = columnInArray;
    }

    public int getXFieldPosition() {
        return xFieldPosition;
    }

    public int getYFieldPosition() {
        return yFieldPosition;
    }

    public int getRowInArray() {
        return rowInArray;
    }

    public int getColumnInArray() {
        return columnInArray;
    }

    public void calc() {
        if (rowInArray == -1) {
            calcRowInArray();
        }
        if (columnInArray == -1) {
            calcColumnInArray();
        }
        calcYFieldPosition();
        calcXFieldPosition();
    }
    
    private void calcRowInArray() {
        rowInArray = calcFieldNumberInArray(yMousePosition);
    }

    private void calcColumnInArray() {
        columnInArray = calcFieldNumberInArray(xMousePosition);
    }

    private void calcXFieldPosition() {
        xFieldPosition = calcFieldPosition(columnInArray);
    }

    private void calcYFieldPosition() {
        yFieldPosition = calcFieldPosition(rowInArray);
    }

    public boolean checkIfClickedInField() {
        calc();
        return Math.hypot(xMousePosition - xFieldPosition,
                yMousePosition - yFieldPosition) < STONE_RADIUS;
    }

    private int calcFieldNumberInArray(double mousePosition) {
        return (int) Math.round((mousePosition) / GraphicBoardSize.GAP_BETWEEN_FIELDS) - 1;
    }

    private int calcFieldPosition(int fieldNumberInArray) {
        return (fieldNumberInArray + 1) * GraphicBoardSize.GAP_BETWEEN_FIELDS;
    }
}
