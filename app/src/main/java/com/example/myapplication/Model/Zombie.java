package com.example.myapplication.Model;

import com.example.myapplication.View.PlayerObserver;

public class Zombie extends Enemy implements PlayerObserver {
    // Former Enemy1
    private MapLayout mapLayout;
    private boolean moveDown = true;
    private double playerX;
    private double playerY;

    public Zombie(float positionX, float positionY, int speed, float scale, int spriteResourceId, MapLayout mapLayout) {
        super(positionX, positionY, speed, scale, spriteResourceId);
        this.mapLayout = mapLayout;
    }

    @Override
    public EnemyType getEnemyType() {
        return EnemyType.ZOMBIE;
    }
    @Override
    public void move() {
        float newPositionY = positionY;
        if (moveDown) {
            if (canMoveDown(this.positionX, this.positionY)) {
                if (!checkCollisionWithPlayer(playerX, positionY + 30)) {
                    this.positionY += this.speed;
                }
            } else {
                moveDown = false;
            }
        } else {
            if (canMoveUp(this.positionX, this.positionY)) {
                if (!checkCollisionWithPlayer(playerX, positionY - 30)) {
                    this.positionY -= this.speed;
                }
            } else {
                moveDown = true;
            }
        }
        if (!checkCollisionWithPlayer(positionX, newPositionY)) {
            this.positionY = newPositionY;
        }
    }

    private boolean canMoveUp(double currentX, double currentY) {
        int tileX = (int) (currentX / 30);
        int tileY = (int) ((currentY - 30) / 30);
        return !mapLayout.isWall(tileY, tileX);
    }

    private boolean canMoveDown(double currentX, double currentY) {
        int tileX = (int) (currentX / 30);
        int tileY = (int) ((currentY + 70) / 30);
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
        // Additional logic specific to Skeleton destruction
    }

    @Override
    public int getScoreValue() {
        return 10;
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
