package com.example.myapplication.Model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;


import org.junit.Test;


public class additionalConfigTests {
    @Test
    public void testLivesEasyDifficulty() {
        Player player = Player.getInstance();
        player.setDifficulty(1);


        assertEquals(100, player.getHealth());
    }


    @Test
    public void testLivesHardDifficulty() {
        Player player = Player.getInstance();
        player.setDifficulty(3);


        assertEquals(100, player.getHealth());
    }
}
