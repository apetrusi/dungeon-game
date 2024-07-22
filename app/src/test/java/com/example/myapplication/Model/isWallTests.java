package com.example.myapplication.Model;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class isWallTests {
    private MapLayout mapLayout;

    @Before
    public void setUp() {
        // Create a MapLayout object with a simple layout for testing.
        int[][] testLayout = {
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0}
        };
        mapLayout = new MapLayout(testLayout);
    }

    @Test
    public void testIsWallOnWallTile() {
        // Verify that a wall tile (row 0, column 2) is correctly identified as a wall.
        assertTrue(mapLayout.isWall(0, 2));
    }

    @Test
    public void testIsWallOnNonWallTile() {
        // Verify that a non-wall tile (row 1, column 1) is correctly identified as not being a wall.
        assertFalse(mapLayout.isWall(1, 1));
    }
}
