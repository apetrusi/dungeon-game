package com.example.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Rect;
/**
 * Responsible for initializing the tiles based on the layout.
 * */
public class TileMap {
    private Tile[][] tiles;
    private TileSet tileSet;
    private int tileSize;

    public TileMap(int[][] layout, TileSet tileSet) {
        this.tileSet = tileSet;
        this.tileSize = tileSet.getTileSize();
        initializeTileMap(layout);
    }

    public int getNumRows() {
        return tiles.length;
    }

    public int getNumColumns() {
        if (tiles.length > 0) {
            return tiles[0].length;
        }
        return 0;
    }

    public int getTileSize() {
        return tileSize;
    }

    private void initializeTileMap(int[][] layout) {
        int rows = layout.length;
        int columns = layout[0].length;
        tiles = new Tile[rows][columns];

        int tileSize = tileSet.getTileSize(); // Get the tile size from your TileSet

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                int tileValue = layout[row][col];

                // Calculate the position of the tile in the grid
                int left = col * tileSize;
                int top = row * tileSize;
                int right = left + tileSize;
                int bottom = top + tileSize;

                Rect tileRect = new Rect(left, top, right, bottom);
                Tile.TileType tileType = Tile.TileType.FLOOR_TILE;
                switch (tileValue) {
                case 0:
                    tileType = Tile.TileType.FLOOR_TILE;
                    break;
                case 1:
                    tileType = Tile.TileType.WALL_TILE;
                    break;
                case 2:
                    tileType = Tile.TileType.EXIT_TILE;
                    break;
                default:
                }
                tiles[row][col] = Tile.getTile(tileType, tileSet, tileRect, tileValue);
            }
        }
    }

    public void draw(Canvas canvas) {
        for (int row = 0; row < tiles.length; row++) {
            for (int col = 0; col < tiles[0].length; col++) {
                Tile tile = tiles[row][col];
                if (tile != null) {
                    tile.draw(canvas);
                }
            }
        }
    }
}