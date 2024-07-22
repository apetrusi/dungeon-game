package com.example.myapplication.View;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.Zombie;
import com.example.myapplication.Model.FirstRoomMapLayout;
import com.example.myapplication.R;

public class Enemy1MovementTests {
    private Enemy enemy1;
    private FirstRoomMapLayout mapLayout = new FirstRoomMapLayout();

    @Before
    public void setUp() {
        enemy1 = new Zombie(900, 600, 20,
                0.1f, R.drawable.fireball, mapLayout);
    }

    @Test
    public void testMoveDown() {
        enemy1.move();
        assertEquals(620, enemy1.getPositionY(), 0.0);
    }

    @Test
    public void testMoveHorizontal() {
        enemy1.move();
        assertEquals(900, enemy1.getPositionX(), 0.0);
    }

    @Test
    public void testMoveUp() {
        enemy1.move();
        assertEquals(620, enemy1.getPositionY(), 0.0);
    }
}