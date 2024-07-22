package com.example.myapplication.Model;


import com.example.myapplication.View.PlayerObserver;

import java.util.Random;

public class Fireball extends Enemy implements PlayerObserver {
    // Former Enemy2
    private MapLayout mapLayout;
    private boolean moveDown = true;
    private Random random = new Random();
    private Direction currentDirection;
    private double playerX;
    private double playerY;

    public Fireball(float positionX, float positionY, int speed, float scale, int spriteResourceId, MapLayout mapLayout) {
        super(positionX, positionY, speed, scale, spriteResourceId);
        this.mapLayout = mapLayout;
        this.currentDirection = getRandomDirection();
    }

    @Override
    public Enemy.EnemyType getEnemyType() {
        return EnemyType.FIREBALL;
    }
    @Override
    public void move() {
        float newPositionY = positionY;
        Direction nextDirection = getRandomDirection();
        
        while (!isValidMove(nextDirection)) {
            nextDirection = getRandomDirection();
        }
        
        switch (nextDirection) {
            case UP:
                if (canMoveUp(positionX, positionY)) {
                    if (!checkCollisionWithPlayer(playerX, positionY - 30)) {
                        positionY -= speed;
                    } else {
                        Player.getInstance().decreaseHealth(EnemyType.FIREBALL);
                    }
                }
                break;
            case DOWN:
                if (canMoveDown(positionX, positionY)) {
                    if (!checkCollisionWithPlayer(playerX, positionY + 30)) {
                        positionY += speed;
                    } else {
                        Player.getInstance().decreaseHealth(EnemyType.FIREBALL);
                    }
                }
                break;
            case LEFT:
                if (canMoveLeft(positionX, positionY)) {
                    if (!checkCollisionWithPlayer(positionX - 30, playerY)) {
                        positionX -= speed;
                    } else {
                        Player.getInstance().decreaseHealth(EnemyType.FIREBALL);
                    }
                }
                break;
            case RIGHT:
                if (canMoveRight(positionX, positionY)) {
                    if (!checkCollisionWithPlayer(positionX + 30, playerY)) {
                        positionX += speed;
                    } else {
                        Player.getInstance().decreaseHealth(EnemyType.FIREBALL);
                    }
                }
                break;
        }
        currentDirection = nextDirection;
        if (!checkCollisionWithPlayer(positionX, newPositionY)) {
            this.positionY = newPositionY;
        }
    }

    private boolean isValidMove(Direction direction) {
        switch (direction) {
            case UP:
                return canMoveUp(positionX, positionY);
            case DOWN:
                return canMoveDown(positionX, positionY);
            case LEFT:
                return canMoveLeft(positionX, positionY);
            case RIGHT:
                return canMoveRight(positionX, positionY);
            default:
                return false;
        }
    }

    private Direction getRandomDirection() {
        int randomNum = random.nextInt(Direction.values().length);
        return Direction.values()[randomNum];
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
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

    private boolean canMoveLeft(double currentX, double currentY) {
        int tileX = (int) ((currentX - 30) / 30);
        int tileY = (int) (currentY / 30);
        return !mapLayout.isWall(tileY, tileX);
    }

    private boolean canMoveRight(double currentX, double currentY) {
        int tileX = (int) ((currentX + 70) / 30);
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
        // Additional logic specific to Skeleton destruction
    }

    @Override
    public int getScoreValue() {
        return 15;
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
