package com.example.myapplication.Model;

public class ScorePowerUp extends PowerUpEffectDecorator {
    public ScorePowerUp(PowerUpEffect powerUpEffect) {
        this.powerUpEffect = powerUpEffect;
    }

    @Override
    public int healthEffect() {
        return powerUpEffect.healthEffect();
    }

    @Override
    public int speedEffect() {
        return powerUpEffect.speedEffect();
    }

    @Override
    public int scoreEffect() {
        return powerUpEffect.scoreEffect() + 30;
    }
}
