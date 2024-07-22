package com.example.myapplication.Model;

public class SpeedPowerUp extends PowerUpEffectDecorator {
    public SpeedPowerUp(PowerUpEffect powerUpEffect) {
        this.powerUpEffect = powerUpEffect;
    }

    @Override
    public int healthEffect() {
        return powerUpEffect.healthEffect();
    }

    @Override
    public int speedEffect() {
        return powerUpEffect.speedEffect() + 20;
    }

    @Override
    public int scoreEffect() {
        return powerUpEffect.scoreEffect();
    }
}
