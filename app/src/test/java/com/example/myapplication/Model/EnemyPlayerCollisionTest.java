package com.example.myapplication.Model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.example.myapplication.R;

public class EnemyPlayerCollisionTest {

    @Test
    public void testFireballCollisionWithPlayer() {
        Fireball fireball = new Fireball(10, 10, 5, 1.0f, R.drawable.fireball, new MapLayout());
        assertTrue(fireball.checkCollisionWithPlayer(20, 20));

        Fireball noCollisionFireball = new Fireball(100, 100, 5, 1.0f, R.drawable.fireball, new MapLayout());
        assertFalse(noCollisionFireball.checkCollisionWithPlayer(20, 20));
    }

    @Test
    public void testGirlZombieCollisionWithPlayer() {
        GirlZombie girlZombie = new GirlZombie(10, 10, 5, 1.0f, R.drawable.girl_zombie, new MapLayout());
        assertTrue(girlZombie.checkCollisionWithPlayer(20, 20));

        GirlZombie noCollisionGirlZombie = new GirlZombie(100, 100, 5, 1.0f, R.drawable.girl_zombie, new MapLayout());
        assertFalse(noCollisionGirlZombie.checkCollisionWithPlayer(20, 20));
    }

    @Test
    public void testSkeletonCollisionWithPlayer() {
        Skeleton skeleton = new Skeleton(10, 10, 5, 1.0f, R.drawable.skeleton, new MapLayout());
        assertTrue(skeleton.checkCollisionWithPlayer(20, 20));

        Skeleton noCollisionSkeleton = new Skeleton(100, 100, 5, 1.0f, R.drawable.skeleton, new MapLayout());
        assertFalse(noCollisionSkeleton.checkCollisionWithPlayer(20, 20));
    }

    @Test
    public void testZombieCollisionWithPlayer() {
        Zombie zombie = new Zombie(10, 10, 5, 1.0f, R.drawable.skeleton2, new MapLayout());
        assertTrue(zombie.checkCollisionWithPlayer(20, 20));

        Zombie noCollisionZombie = new Zombie(100, 100, 5, 1.0f, R.drawable.skeleton2, new MapLayout());
        assertFalse(noCollisionZombie.checkCollisionWithPlayer(20, 20));
    }
}
