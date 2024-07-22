package com.example.myapplication.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.myapplication.R;
/**
 * Responsible for managing and providing access to a bitmap image.
 * */
public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 16;
    private static final int SPRITE_HEIGHT_PIXELS = 16;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.characterset, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray() {
        Sprite[] spriteArray = new Sprite[3];
        spriteArray[0] = new Sprite(getBitmap(), new Rect(0 * 16, 0, 1 * 16, 16));
        spriteArray[1] = new Sprite(getBitmap(), new Rect(1 * 16, 0, 2 * 16, 16));
        spriteArray[2] = new Sprite(getBitmap(), new Rect(2 * 16, 0, 3 * 16, 2 * 16));

        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getFloorSprite() {
        return spriteByIndex(1, 0);
    }

    public Sprite getWallSprite() {
        return spriteByIndex(1, 1);
    }

    private Sprite spriteByIndex(int row, int col) {
        return new Sprite(getBitmap(), new Rect(
                col * SPRITE_WIDTH_PIXELS,
                row * SPRITE_HEIGHT_PIXELS,
                (col + 1) * SPRITE_WIDTH_PIXELS,
                (row + 1) * SPRITE_HEIGHT_PIXELS));
    }



}
