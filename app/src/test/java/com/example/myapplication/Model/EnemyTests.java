package com.example.myapplication.Model;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertFalse;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.ViewModel.EnemyFactory;

public class EnemyTests {
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Enemy enemy4;
    private Enemy[] enemies;

    @Before
    public void setUp() {
        enemies = new Enemy[4];

        enemy1 = EnemyFactory.createEnemy("Zombie", null);
        enemy2 = EnemyFactory.createEnemy("Fireball", null);
        enemy3 = EnemyFactory.createEnemy("Skeleton", null);
        enemy4 = EnemyFactory.createEnemy("GirlZombie", null);

        enemies[0] = enemy1;
        enemies[1] = enemy2;
        enemies[2] = enemy3;
        enemies[3] = enemy4;
    }

    @Test
    public void testSpriteDiff() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (enemies[i] != enemies[j])
                    assertFalse(enemies[i].getSpriteResourceId() == enemies[j].getSpriteResourceId());
            }
        }
    }

    @Test
    public void testSpeedDiff() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (enemies[i] != enemies[j])
                    assertFalse(enemies[i].getSpeed() == enemies[j].getSpeed());
            }
        }
    }
}
