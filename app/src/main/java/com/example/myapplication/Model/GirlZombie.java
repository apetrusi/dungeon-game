package com.example.myapplication.Model;

import com.example.myapplication.View.PlayerObserver;

import java.util.Random;

public class GirlZombie extends Enemy implements PlayerObserver {
    private MapLayout mapLayout;
    private Random random = new Random();
    private int currentDirection;
    private double playerX;
    private double playerY;

    public GirlZombie(float positionX, float positionY, int speed, float scale, int spriteResourceId, MapLayout mapLayout) {
        super(positionX, positionY, speed, scale, spriteResourceId);
        this.mapLayout = mapLayout;
        this.currentDirection = getRandomAngle();
    }

    @Override
    public Enemy.EnemyType getEnemyType() {
        return EnemyType.GIRL_ZOMBIE;
    }
    @Override
    public void move() {
        float newPositionY = positionY;
        int nextDirection = getRandomAngle();

        while (!isValidMove(nextDirection)) {
            nextDirection = getRandomAngle();
        }

        switch (nextDirection) {
            case 1:
                if (canMoveUp(positionX, positionY) && canMoveLeft(positionX, positionY)) {
                    if (!checkCollisionWithPlayer(playerX, positionY - 30)) {
                        positionX -= speed;
                        positionY -= speed;
                    }
                }
                break;
            case 2:
                if (canMoveDown(positionX, positionY) && canMoveRight(positionX, positionY)) {
                    if (!checkCollisionWithPlayer(playerX, positionY + 30)) {
                        positionX += speed;
                        positionY += speed;
                    }
                }
                break;
            case 3:
                if (canMoveLeft(positionX, positionY) && canMoveDown(positionX, positionY)) {
                    if (!checkCollisionWithPlayer(positionX - 30, playerY)) {
                        positionX -= speed;
                        positionY += speed;
                    }
                }
                break;
            case 4:
                if (canMoveRight(positionX, positionY) && canMoveUp(positionX, positionY)) {
                    if (!checkCollisionWithPlayer(positionX + 30, playerY)) {
                        positionX += speed;
                        positionY -= speed;
                    }
                }
                break;
        }
        currentDirection = nextDirection;
        if (!checkCollisionWithPlayer(positionX, newPositionY)) {
            this.positionY = newPositionY;
        }
    }

    private boolean isValidMove(int direction) {
        switch (direction) {
            case 1:
                return canMoveUp(positionX, positionY) && canMoveLeft(positionX, positionY);
            case 2:
                return canMoveDown(positionX, positionY) && canMoveRight(positionX, positionY);
            case 3:
                return canMoveLeft(positionX, positionY) && canMoveDown(positionX, positionY);
            case 4:
                return canMoveRight(positionX, positionY) && canMoveUp(positionX, positionY);
            default:
                return false;
        }
    }

    private int getRandomAngle() {
        int randomNum = random.nextInt(4);
        return (randomNum + 1);
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
