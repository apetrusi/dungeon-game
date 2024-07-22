package com.example.myapplication.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * The class is responsible for loading and rendering the tiles.
 * */
public class TileSet {
    private Map<Tile.TileType, Bitmap> tilesetImages; // Store tileset images by type
    private int tileSize;
    private int columns;
    private int rows;

    public TileSet(Context context, int floorTilesetImageResId, int wallTilesetImageResId,
                   int exitTilesetImageResId, int tileSize, int columns, int rows) {
        // Load the tileset images from resources
        tilesetImages = new HashMap<>();
        tilesetImages.put(Tile.TileType.FLOOR_TILE, BitmapFactory.decodeResource(
                context.getResources(), floorTilesetImageResId));
        tilesetImages.put(Tile.TileType.WALL_TILE, BitmapFactory.decodeResource(
                context.getResources(), wallTilesetImageResId));
        tilesetImages.put(Tile.TileType.EXIT_TILE, BitmapFactory.decodeResource(
                context.getResources(), exitTilesetImageResId));

        this.tileSize = tileSize;
        this.columns = columns;
        this.rows = rows;
    }

    public Bitmap getTile(Tile.TileType tileType, int index) {
        // Calculate the position of the tile in the tileset
        int x = (index % columns) * tileSize;
        int y = (index / columns) * tileSize;

        // Ensure that the x and y values are within the bounds of the bitmap
        if (x < 0) {
            x = 0;
        } else if (x + tileSize > tilesetImages.get(tileType).getWidth()) {
            x = tilesetImages.get(tileType).getWidth() - tileSize;
        }

        if (y < 0) {
            y = 0;
        } else if (y + tileSize > tilesetImages.get(tileType).getHeight()) {
            y = tilesetImages.get(tileType).getHeight() - tileSize;
        }

        // Extract and return the tile as a Bitmap based on tile type
        Bitmap tilesetImage = tilesetImages.get(tileType);
        if (tilesetImage != null) {
            return Bitmap.createBitmap(tilesetImage, x, y, tileSize, tileSize);
        } else {
            return null;
        }
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
