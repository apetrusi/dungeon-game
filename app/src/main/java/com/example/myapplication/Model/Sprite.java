package com.example.myapplication.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
/**
 * Responsible for representing and rendering a 2D sprite using a provided Bitmap.
 * */
public class Sprite {
    private final Bitmap tileBitmap; // Use Bitmap instead of SpriteSheet
    private final Rect rect;

    public Sprite(Bitmap tileBitmap, Rect rect) {
        this.tileBitmap = tileBitmap;
        this.rect = rect;
    }

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                tileBitmap,
                rect,
                new Rect(x, y, x + rect.width(), y + rect.height()), // Use rect's width and height
                null
        );
    }
}
