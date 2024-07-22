package com.example.myapplication.Model;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

import org.junit.Test;


public class ConfigurationTests {

    @Test
    public void testValidUsername() {
        Player p = Player.getInstance();

        assertTrue(p.isUsernameValid("ValidUsername"));

        assertFalse(p.isUsernameValid(""));

        assertFalse(p.isUsernameValid("    "));

    }

    @Test
    public void testLivesMediumDifficulty() {
        Player player = Player.getInstance();
        player.setDifficulty(2);

        assertEquals(0, player.getHealth());
    }

}
