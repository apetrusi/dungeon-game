package com.example.myapplication.Model;

import com.example.myapplication.View.PlayerObserver;

public class PlayerMovement implements EntityMovement {
    private MapLayout mapLayout;
    private Player player;

    public void setMapLayout(MapLayout mapLayout) {
        this.mapLayout = mapLayout;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public double moveLeft(double currentX) {
        if (currentX > 30 && canMoveLeft(currentX, Player.getInstance().getPositionY())) {
            if (!checkCollisionWithEnemies(currentX - 30, Player.getInstance().getPositionY())) {
                return currentX - player.getSpeed();
            }
        }
        return currentX;
    }

    @Override
    public double moveRight(double currentX) {
        if (canMoveRight(currentX, Player.getInstance().getPositionY())) {
            if (!checkCollisionWithEnemies(currentX + 30, Player.getInstance().getPositionY())) {
                return currentX + player.getSpeed();
            }
        }
        return currentX;
    }

    @Override
    public double moveUp(double currentY) {
        if (currentY > 100 && canMoveUp(Player.getInstance().getPositionX(), currentY)) {
            if (!checkCollisionWithEnemies(Player.getInstance().getPositionX(), currentY - 30)) {
                return currentY - player.getSpeed();
            }
        }
        return currentY;
    }

    @Override
    public double moveDown(double currentY) {
        if (canMoveDown(Player.getInstance().getPositionX(), currentY)) {
            if (!checkCollisionWithEnemies(Player.getInstance().getPositionX(), currentY + 30)) {
                return currentY + player.getSpeed();
            }
        }
        return currentY;
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

    private boolean checkCollisionWithEnemies(double newX, double newY) {
        for (PlayerObserver observer : Player.getInstance().getObservers()) {
            if (observer instanceof Enemy) {
                Enemy enemy = (Enemy) observer;
                Enemy.EnemyType enemyType = enemy.getEnemyType();
                if (enemy.checkCollisionWithPlayer(newX, newY)) {
                    Player.getInstance().decreaseHealth(enemyType);
                    return true;
                }
            }
        }
        return false;
    }
}