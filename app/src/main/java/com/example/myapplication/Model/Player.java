package com.example.myapplication.Model;

import static androidx.constraintlayout.widget.ConstraintProperties.LEFT;
import static androidx.constraintlayout.widget.ConstraintProperties.RIGHT;
import static java.math.RoundingMode.DOWN;
import static java.math.RoundingMode.UP;

import android.graphics.Path;
import android.os.Handler;

import com.example.myapplication.R;
import com.example.myapplication.View.PlayerObserver;
import com.example.myapplication.ViewModel.WeaponView;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a basic model for a player character in the game.
 * */
public class Player extends PowerUpEffect{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int lives) {
        this.health = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDifficulty() {
        return difficulty;
    }

    private static String name;
    private static int health;
    private static int score;
    private static int speed;
    private static int damage;
    private static int difficulty;
    private static Player instance;
    private static double positionX;
    private static double positionY;
    private EntityMovement entityMovement;

    public void setEntityMovement(EntityMovement entityMovement) {
        this.entityMovement = entityMovement;
    }
    public Player(String name, int health, int score, int speed, int difficulty,
                  double positionX, double positionY) {
        this.name = name;
        this.health = health;
        this.score = score;
        this.speed = speed;
        this.difficulty = difficulty;
        this.positionX = positionX;
        this.positionY = positionY;
        this.currentWeapon = new Weapon("Arrow",10);
    }

    public boolean isUsernameValid(String username) {
        return username != null && !username.isEmpty() && !username.trim().equals("");
    }
    public static Player getInstance() {
        if (instance == null) {
            instance = new Player(name, health, score, speed, difficulty, positionX, positionY);
        }
        return instance;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public void update() {

    }
    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }
    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }


    public void moveLeft() {
        if (positionX > 20) {
            positionX = entityMovement.moveLeft(positionX);
            notifyObservers();
        }
    }

    public void moveRight() {
        positionX = entityMovement.moveRight(positionX);
        notifyObservers();
    }

    public void moveUp() {
        if (positionY > 100) {
            positionY = entityMovement.moveUp(positionY);
            notifyObservers();
        }
    }

    public void moveDown() {
        positionY = entityMovement.moveDown(positionY);
        notifyObservers();
    }

    private List<PlayerObserver> observers = new ArrayList<>();

    public List<PlayerObserver> getObservers() {
        return observers;
    }

    public void addObserver(Enemy enemy) {
        if (enemy instanceof PlayerObserver) {
            observers.add((PlayerObserver) enemy);
        }
    }

    public void removeObserver(PlayerObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (PlayerObserver observer : observers) {
            observer.updatePlayerPosition(positionX, positionY);
        }
    }

    public void decreaseHealth(Enemy.EnemyType enemyType) {
        int damage = 0;
        switch (enemyType) {
            case ZOMBIE:
                damage = 3; // Double damage for TYPE1 enemy
                break;
            case FIREBALL:
                damage = 5; // Halve damage for TYPE2 enemy
                break;
            case GIRL_ZOMBIE:
                damage = 3;
                break;
            case SKELETON:
                damage = 8;
                break;
        }
        health -= damage * difficulty;
        if (health <= 0) {
            // Player is defeated, handle game over logic here
        }
    }

    @Override
    public int healthEffect() {
        return health;
    }

    @Override
    public int speedEffect() {
        return speed;
    }

    @Override
    public int scoreEffect() {
        return score;
    }

    private Weapon currentWeapon;
    private int facingDirection;

    public void setFacingDirection(int direction) {
        this.facingDirection = direction;
    }
    public int getFacingDirection() { return facingDirection; }

    private boolean isAttacking = false;

    public void attack(WeaponView weaponView) {
        if (currentWeapon != null && facingDirection != -1 && !isAttacking) {
            isAttacking = true;
            weaponView.startWeaponAnimation();

            // Use a Handler to stop the animation after a short duration
            new Handler().postDelayed(() -> {
                isAttacking = false;
                weaponView.stopWeaponAnimation();

                // Identify the target enemy in the direction of the attack
                Enemy targetEnemy = getTargetEnemyInDirection(facingDirection);
                if (targetEnemy != null) {
                    // Update player score or perform other actions based on the destruction
                    targetEnemy.destroy();
                    int currentScore = Player.getInstance().getScore();
                    Player.getInstance().setScore(currentScore + targetEnemy.getScoreValue());
                }
            }, 1000); // Adjust the duration as needed
        }
    }

    public Enemy getTargetEnemyInDirection(int direction) {
        // Determine the position of the player's attack based on the direction
        double attackX = positionX;
        double attackY = positionY;

        switch (direction) {
            case 0: // UP
                attackY -= ATTACK_RANGE;
                break;
            case 1: // DOWN
                attackY += ATTACK_RANGE;
                break;
            case 2: // LEFT
                attackX -= ATTACK_RANGE;
                break;
            case 3: // RIGHT
                attackX += ATTACK_RANGE;
                break;
        }

        // Iterate through the list of enemies and check if any are in the attack range
        for (PlayerObserver observer : observers) {
            if (observer instanceof Enemy) {
                Enemy enemy = (Enemy) observer;

                // Check if the enemy is within the attack range
                if (isEnemyInAttackRange(enemy, attackX, attackY)) {
                    return enemy;  // Return the target enemy
                }
            }
        }

        return null;  // No enemy found in the attack range
    }

    protected boolean isEnemyInAttackRange(Enemy enemy, double attackX, double attackY) {
        double enemyX = enemy.getPositionX();
        double enemyY = enemy.getPositionY();

        // Check if the enemy is within the attack range
        return (enemyX >= attackX - ATTACK_RANGE / 2 && enemyX <= attackX + ATTACK_RANGE / 2) &&
                (enemyY >= attackY - ATTACK_RANGE / 2 && enemyY <= attackY + ATTACK_RANGE / 2);
    }

    private static final double ATTACK_RANGE = 108;

    public void attackCheck(Enemy targetEnemy) {
        while (!targetEnemy.getIsDestroyed()) {
            targetEnemy.destroy();
        }
        int currentScore = getScore();
        setScore(currentScore + targetEnemy.getScoreValue());
    }
}