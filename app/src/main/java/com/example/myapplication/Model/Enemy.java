package com.example.myapplication.Model;

import android.view.ViewGroup;

import com.example.myapplication.ViewModel.EnemyView;

/**
 * Defines a basic model of a enemy character in the game.
 */
public abstract class Enemy {
    protected int speed;

    protected float positionX;
    protected float positionY;
    protected float scale;
    protected int spriteResourceId;

    public Enemy(float positionX, float positionY, int speed, float scale, int spriteResourceId) {
        this.speed = speed;
        this.positionX = positionX;
        this.positionY = positionY;
        this.scale = scale;
        this.spriteResourceId = spriteResourceId;
    }

    public enum EnemyType {
        ZOMBIE,
        FIREBALL,
        SKELETON,
        GIRL_ZOMBIE
    }

    public abstract EnemyType getEnemyType();

    public abstract void move();

    public float getPositionX() {
        return positionX;
    }
    public float getPositionY() {
        return positionY;
    }
    public int getSpeed() { return speed; }

    public float getScale() { return scale; }
    public int getSpriteResourceId() {
        return spriteResourceId;
    }
    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }
    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    public boolean checkCollisionWithPlayer(double playerPosX, double playerPosY) {
        if (isDestroyed) {
            return false;  // If the enemy is destroyed, no collision
        }

        double playerLeft = playerPosX;
        double playerRight = playerPosX + 60;
        double playerTop = playerPosY;
        double playerBottom = playerPosY + 60;

        double enemyLeft = positionX;
        double enemyRight = positionX + 60;
        double enemyTop = positionY;
        double enemyBottom = positionY + 60;

        // Check for collision
        return (playerRight > enemyLeft) && (playerLeft < enemyRight) &&
        (playerBottom > enemyTop) && (playerTop < enemyBottom);
    }
    private EnemyView enemyView;
    protected boolean isDestroyed = false;
    public abstract void destroy();
    public int destroyTest() {
        isDestroyed = true;
        return this.getScoreValue();
    }
    public abstract int getScoreValue();
    public boolean getIsDestroyed() {
        return isDestroyed;
    }
    public void setEnemyView(EnemyView enemyView) {
        this.enemyView = enemyView;
    }

    protected void removeEnemyFromGameWorld() {
        // Remove the associated view from its parent layout
        if (enemyView != null) {
            ViewGroup parent = (ViewGroup) enemyView.getParent();
            if (parent != null) {
                parent.removeView(enemyView);
            }
        }
    }
}
