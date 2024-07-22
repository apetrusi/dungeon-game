package com.example.myapplication.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerMovementTest {
    private Player player = Player.getInstance();
    private PlayerMovement movement = new PlayerMovement();
    private FirstRoomMapLayout mapLayout = new FirstRoomMapLayout();

    @Before
    public void setUp() {
        movement.setPlayer(player);
        movement.setMapLayout(mapLayout);
        Player.getInstance().setEntityMovement(movement);
        player.setSpeed(10);
        Player.getInstance().setPositionX(400);
        Player.getInstance().setPositionY(400);
    }

    @Test
    public void testMoveLeft() {
        player.moveLeft();
        assertEquals(390, player.getPositionX(), 0.0);
    }

    @Test
    public void testMoveRight() {
        player.moveRight();
        assertEquals(410, player.getPositionX(), 0.0);
    }

    @Test
    public void testMoveUp() {
        player.moveUp();
        assertEquals(390, player.getPositionY(), 0.0);
    }

    @Test
    public void testMoveDown() {
        player.moveDown();
        assertEquals(410, player.getPositionY(), 0.0);
    }
}
