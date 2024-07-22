package com.example.myapplication.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
/**
 * Responsible for representing and rendering the exit tile.
 * */
public class ExitTile extends Tile {
    private final TileSet tileSet;
    private final int tileIndex;

    public ExitTile(TileSet tileSet, Rect mapLocationRect, int tileIndex) {
        super(tileSet, mapLocationRect);
        this.tileSet = tileSet;
        this.tileIndex = tileIndex;
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap tileBitmap = tileSet.getTile(TileType.EXIT_TILE, tileIndex);
        canvas.drawBitmap(tileBitmap, null, mapLocationRect, null);
    }

    @Override
    public TileType getTileType() {
        return TileType.EXIT_TILE;
    }
}