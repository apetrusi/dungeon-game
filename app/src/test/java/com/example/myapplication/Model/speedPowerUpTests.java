package com.example.myapplication.Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class speedPowerUpTests {
    private SpeedPowerUp speedPowerUp;

    @Before
    public void setUp() {
        // Simple concrete implementation of the abstract PowerUpEffect class
        PowerUpEffect baseEffect = new PowerUpEffect() {
            @Override
            public int healthEffect() {
                return 10; // Example health effect
            }

            @Override
            public int speedEffect() {
                return 5; // Example speed effect
            }

            @Override
            public int scoreEffect() {
                return 15; // Example score effect
            }
        };

        speedPowerUp = new SpeedPowerUp(baseEffect);
    }

    @Test
    public void testSpeedEffect() {
        // Test speed effect is correctly increased by 20
        assertEquals(25, speedPowerUp.speedEffect());
    }

    @Test
    public void testHealthEffectUnchanged() {
        // Test  health effect remains unchanged
        assertEquals(10, speedPowerUp.healthEffect());
    }
}
