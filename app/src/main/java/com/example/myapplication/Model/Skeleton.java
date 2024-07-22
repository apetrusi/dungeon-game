package com.example.myapplication.Model;

import com.example.myapplication.View.PlayerObserver;
import com.example.myapplication.ViewModel.EnemyView;

public class Skeleton extends Enemy implements PlayerObserver {
    private MapLayout mapLayout;
    private boolean moveLeft = true;
    private double playerX;
    private double playerY;

    public Skeleton(float positionX, float positionY, int speed, float scale, int spriteResourceId, MapLayout mapLayout) {
        super(positionX, positionY, speed, scale, spriteResourceId);
        this.mapLayout = mapLayout;
    }

    @Override
    public EnemyType getEnemyType() {
        return EnemyType.SKELETON;
    }

    @Override
    public void move() {
        float newPositionY = positionY;
        if (moveLeft) {
            if (canMoveLeft(this.positionX, this.positionY)) {
                if (!checkCollisionWithPlayer(positionX - 30, playerY)) {
                    this.positionX -= this.speed;
                }
            } else {
                moveLeft = false;
            }
        } else {
            if (canMoveRight(this.positionX, this.positionY)) {
                if (!checkCollisionWithPlayer(positionX + 30, playerY)) {
                    this.positionX += this.speed;
                }
            } else {
                moveLeft = true;
            }
        }
        if (!checkCollisionWithPlayer(positionX, newPositionY)) {
            this.positionY = newPositionY;
        }
    }

    private boolean canMoveRight(double currentX, double currentY) {
        int tileX = (int) ((currentX + 70) / 30);
        int tileY = (int) (currentY / 30);
        return !mapLayout.isWall(tileY, tileX);
    }

    private boolean canMoveLeft(double currentX, double currentY) {
        int tileX = (int) ((currentX - 30) / 30);
        int tileY = (int) (currentY / 30);
        return !mapLayout.isWall(tileY, tileX);
    }

    @Override
    public void updatePlayerPosition(double playerX, double playerY) {
        this.playerX = playerX;
        this.playerY = playerY;
    }

    @Override
    public void destroy() {
        removeEnemyFromGameWorld();
        isDestroyed = true;
    }

    @Override
    public int getScoreValue() {
        return 20;
    }

    @Override
    public float getPositionX() {
        return isDestroyed ? -100 : positionX;
    }

    @Override
    public float getPositionY() {
        return isDestroyed ? -100 : positionY;
    }
}