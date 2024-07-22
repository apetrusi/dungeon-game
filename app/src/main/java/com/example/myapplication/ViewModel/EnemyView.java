package com.example.myapplication.ViewModel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.View;

import com.example.myapplication.Model.Enemy;

// Do we need this class?

public class EnemyView extends View {
    private Bitmap enemySprite;
    private float x, y;
    private float scale;
    public EnemyView(Context context, float x, float y, float scale, int spriteResourceId) {
        super(context);
        this.x = x;
        this.y = y;
        this.scale = scale;
        Bitmap originalSprite = BitmapFactory.decodeResource(getResources(), spriteResourceId);

        // Create a matrix for scaling
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Scale the sprite
        enemySprite = Bitmap.createBitmap(originalSprite, 0, 0, originalSprite.getWidth(), originalSprite.getHeight(), matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(enemySprite, x, y, null);
    }

    public void updatePosition(float newX, float newY) {
        x = newX;
        y = newY;
        invalidate();
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public Bitmap getEnemySprite() {
        return enemySprite;
    }

}
