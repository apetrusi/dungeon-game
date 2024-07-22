package com.example.myapplication.Model;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 30;
    public static final int TILE_HEIGHT_PIXELS = 30;
    protected int[][] layout; // A 2D integer array representing the map grid.

    // Default constructor
    public MapLayout() {
        // Initialize the layout with default values.
        this.layout = new int[40][80];
        initializeLayout();
    }

    // Parameterized constructor
    public MapLayout(int[][] layout) {
        this.layout = layout;
    }

    public int[][] getLayout() {
        return layout;
    }

    public int getColumns() {
        return layout[0].length;
    }

    public int getRows() {
        return layout.length;
    }

    public boolean isWall(int row, int column) {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            return false;
        }
        return layout[row][column] == 1;
    }

    public boolean isExit(int row, int column) {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            return false;
        }
        return layout[row][column] == 2;
    }

    private void initializeLayout() {
    }
}