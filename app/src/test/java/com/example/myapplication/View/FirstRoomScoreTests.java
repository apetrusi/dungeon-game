package com.example.myapplication.View;

import static com.example.myapplication.View.FirstRoomActivity.scoreUpdate;
import static org.junit.Assert.*;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.ViewModel.EnemyFactory;

import org.junit.Before;
import org.junit.Test;

public class FirstRoomScoreTests {
    private Enemy enemy1;
    private Enemy enemy2;
    @Before
    public void setUp() {
        enemy1 = EnemyFactory.createEnemy("Zombie", null);
        enemy2 = EnemyFactory.createEnemy("Skeleton", null);
    }
    @Test
    public void testScoreUpdateOnZombie() {
        int score = scoreUpdate(enemy1, 0);
        assertEquals(score, 10);
    }
    @Test
    public void testScoreUpdateOnSkeleton() {
        int score = scoreUpdate(enemy2, 10);
        assertEquals(score, 30);
    }
}