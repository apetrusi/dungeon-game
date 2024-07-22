package com.example.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Rect;
/**
 * Responsible for representing and rendering different types of tiles.
 * */
public abstract class Tile {
    protected final Rect mapLocationRect;
    protected final TileSet tileSet;

    public Tile(TileSet tileSet, Rect mapLocationRect) {
        this.tileSet = tileSet;
        this.mapLocationRect = mapLocationRect;
    }

    public enum TileType {
        FLOOR_TILE,
        WALL_TILE,
        EXIT_TILE
    }

    public abstract void draw(Canvas canvas);

    public abstract TileType getTileType();

    public static Tile getTile(TileType tileType, TileSet tileSet, Rect tileRect, int tileIndex) {
        switch (tileType) {
        case FLOOR_TILE:
            return new FloorTile(tileSet, tileRect, tileIndex);
        case WALL_TILE:
            return new WallTile(tileSet, tileRect, tileIndex);
        case EXIT_TILE:
            return new ExitTile(tileSet, tileRect, tileIndex);
        default:
            return null;
        }
    }

}