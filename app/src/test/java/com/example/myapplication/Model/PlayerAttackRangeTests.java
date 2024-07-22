package com.example.myapplication.Model;

import com.example.myapplication.R;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerAttackRangeTests {

    private static final double ATTACK_RANGE = 108;

    @Test
    public void testIsEnemyInAttackRange() {
        // Create a player and set its position
        Player player = new Player("TestPlayer", 100, 0, 5, 1, 100, 100);

        FirstRoomMapLayout mapLayout = new FirstRoomMapLayout();

        // Create a Skeleton enemy and set its position
        Enemy enemy = new Skeleton(150, 150, 1, 1.0f, R.drawable.skeleton, mapLayout);

        // Call isEnemyInAttackRange with a position that is within the attack range
        boolean result = player.isEnemyInAttackRange(enemy, 120, 120);

        // Assert that the result is true since the enemy is within the attack range
        assertTrue(result);
    }

    @Test
    public void testIsEnemyNotInAttackRange() {
        // Create a player and set its position
        Player player = new Player("TestPlayer", 100, 0, 5, 1, 100, 100);

        FirstRoomMapLayout mapLayout = new FirstRoomMapLayout();

        // Create a Skeleton enemy and set its position
        Enemy enemy = new Skeleton(200, 200, 1, 1.0f, R.drawable.skeleton, mapLayout);

        // Call isEnemyInAttackRange with a position that is outside the attack range
        boolean result = player.isEnemyInAttackRange(enemy, 120, 120);

        // Assert that the result is false since the enemy is outside the attack range
        assertFalse(result);
    }

}