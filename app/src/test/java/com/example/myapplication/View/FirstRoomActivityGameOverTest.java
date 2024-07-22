package com.example.myapplication.View;

import static com.example.myapplication.View.FirstRoomActivity.moveToGameOver;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class FirstRoomActivityGameOverTest {
    @Test
    public void testDoNotMoveWhenHPNotZero() {
        int playerHealth = 12;
        boolean moveToGameOver = moveToGameOver(playerHealth);
        assertFalse(moveToGameOver);
    }
    @Test
    public void testMovesWhenHPIsZero() {
        int playerHealth = 0;
        boolean moveToGameOver = moveToGameOver(playerHealth);
        assertTrue(moveToGameOver);
    }
}