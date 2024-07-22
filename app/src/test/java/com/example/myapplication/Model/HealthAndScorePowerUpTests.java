package com.example.myapplication.Model;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HealthAndScorePowerUpTests {
    private Player player = Player.getInstance();
    private PowerUpEffect powerUpEffect;

    @Before
    public void setUp() {
        player.setHealth(100);
        player.setScore(1000);
    }

    @Test
    public void testHealthPowerUp() {
        powerUpEffect = new HealthPowerUp(player);
        player.setHealth(powerUpEffect.healthEffect());
        assertEquals(120, player.getHealth());
    }

    @Test
    public void testScoreUnchanged() {
        powerUpEffect = new HealthPowerUp(player);
        player.setHealth(powerUpEffect.healthEffect());
        assertEquals(1000, player.getScore());
    }

    @Test
    public void testScorePowerUp() {
        powerUpEffect = new ScorePowerUp(player);
        player.setScore(powerUpEffect.scoreEffect());
        assertEquals(1030, player.getScore());
    }

    @Test
    public void testHealthUnchanged() {
        powerUpEffect = new ScorePowerUp(player);
        player.setScore(powerUpEffect.scoreEffect());
        assertEquals(100, player.getHealth());
    }


}
