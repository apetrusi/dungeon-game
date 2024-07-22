package com.example.myapplication.Model;

public class HealthPowerUp extends PowerUpEffectDecorator {
    public HealthPowerUp(PowerUpEffect powerUpEffect) {
        this.powerUpEffect = powerUpEffect;
    }

    @Override
    public int healthEffect() {
        return powerUpEffect.healthEffect() + 20;
    }

    @Override
    public int speedEffect() {
        return powerUpEffect.speedEffect();
    }

    @Override
    public int scoreEffect() {
        return powerUpEffect.scoreEffect();
    }
}
