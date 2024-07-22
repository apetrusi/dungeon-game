package com.example.myapplication.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import android.content.res.Resources;

public class PlayerCollisionTest {
    private Player player = Player.getInstance();

//    private int height = 1800;
//    private int width = 2560;

    @Before
    public void setUp() {
        player.setEntityMovement(new PlayerMovement());
        player.setPositionX(31);
        player.setPositionY(101);
    }

    @Test
    public void testTooFarUp() {
        player.setPositionY(50);
        player.moveUp();
        assertEquals(50, player.getPositionY(), 0.0);
    }

//    @Test
//    public void testTooFarDown() {
//        player.setPositionY(height - 29);
//        player.moveDown();
//        assertEquals(height, player.getPositionY(), 0.0);
//    }

    @Test
    public void testTooFarLeft() {
        player.setPositionX(20);
        player.moveLeft();
        assertEquals(20, player.getPositionX(), 0.0);
    }

//    @Test
//    public void testTooFarRight() {
//        player.setPositionX(width - 69);
//        player.moveRight();
//        assertEquals(width, player.getPositionX(), 0.0);
//    }
}
