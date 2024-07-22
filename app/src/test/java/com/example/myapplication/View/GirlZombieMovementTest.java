package com.example.myapplication.View;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.GirlZombie;
import com.example.myapplication.Model.FirstRoomMapLayout;
import com.example.myapplication.R;


public class GirlZombieMovementTest {
    private Enemy girlZombie;
    private FirstRoomMapLayout mapLayout = new FirstRoomMapLayout();

    @Before
    public void setUp() {
        girlZombie = new GirlZombie(820, 600, 20,
                0.1f, R.drawable.girl_zombie, mapLayout);
    }

    @Test
    public void testMoveDown() {
        girlZombie.move();
        assertEquals(620, girlZombie.getPositionY(), 0.0);
    }

    @Test
    public void testMoveHorizontal() {
        girlZombie.move();
        boolean condition = girlZombie.getPositionX() == 800 || girlZombie.getPositionX() == 840;
        assertTrue(condition);
    }

    @Test
    public void testMoveUp() {
        girlZombie.move();
        assertEquals(620, girlZombie.getPositionY(), 0.0);
    }

}
