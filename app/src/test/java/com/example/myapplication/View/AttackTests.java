package com.example.myapplication.View;

import com.example.myapplication.Model.FirstRoomMapLayout;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.Enemy;
import com.example.myapplication.ViewModel.EnemyFactory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AttackTests {
    private Player player = Player.getInstance();
    private Enemy enemy;
    private FirstRoomMapLayout mapLayout;

    @Before
    public void setUp() {
        mapLayout = new FirstRoomMapLayout();
        player.setScore(1000);
        player.setPositionX(100);
        player.setPositionY(100);
    }

    @Test
    public void testZombieHealthDecrease() {
        enemy = EnemyFactory.createEnemy("Zombie", mapLayout);
        player.attackCheck(enemy);
        assertEquals(true, enemy.getIsDestroyed());
    }

    @Test
    public void testGirlZombieHealthDecrease() {
        enemy = EnemyFactory.createEnemy("GirlZombie", mapLayout);
        player.attackCheck(enemy);
        assertEquals(true, enemy.getIsDestroyed());
    }
    @Test
    public void testSkeletonHealthDecrease() {
        enemy = EnemyFactory.createEnemy("Skeleton", mapLayout);
        player.attackCheck(enemy);
        assertEquals(true, enemy.getIsDestroyed());
    }
    @Test
    public void testFireballHealthDecrease() {
        enemy = EnemyFactory.createEnemy("Fireball", mapLayout);
        player.attackCheck(enemy);
        assertEquals(true, enemy.getIsDestroyed());
    }
}

