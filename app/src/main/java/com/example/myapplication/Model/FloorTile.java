package com.example.myapplication.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
/**
 * Responsible for representing and rendering the floor tile.
 * */
public class FloorTile extends Tile {
    private final TileSet tileSet;
    private final int tileIndex;

    public FloorTile(TileSet tileSet, Rect mapLocationRect, int tileIndex) {
        super(tileSet, mapLocationRect);
        this.tileSet = tileSet;
        this.tileIndex = tileIndex;
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap tileBitmap = tileSet.getTile(Tile.TileType.FLOOR_TILE, tileIndex);
        canvas.drawBitmap(tileBitmap, null, mapLocationRect, null);
    }

    @Override
    public TileType getTileType() {
        return TileType.FLOOR_TILE;
    }
}