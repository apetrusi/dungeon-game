package com.example.myapplication.Model;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class ThirdRoomMapLayoutExitTests {
    @Test
    public void testIsExitOnExitTile() {
        ThirdRoomMapLayout mapLayout = new ThirdRoomMapLayout();
        assertTrue(mapLayout.isExit(13, 69));
    }

    @Test
    public void testIsExitOnNonExitTile() {
        ThirdRoomMapLayout mapLayout = new ThirdRoomMapLayout();
        assertFalse(mapLayout.isExit(1, 1));
    }
}