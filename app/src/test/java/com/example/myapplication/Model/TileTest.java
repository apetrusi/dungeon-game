package com.example.myapplication.Model;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class TileTest {
    FirstRoomMapLayout first;
    SecondRoomMapLayout second;
    ThirdRoomMapLayout third;

    @Before
    public void setUp() {
        first = new FirstRoomMapLayout();
        second = new SecondRoomMapLayout();
        third = new ThirdRoomMapLayout();
    }
    @Test
    public void checkFirstLayoutVisibility() throws NoSuchFieldException {
        Field layout = FirstRoomMapLayout.class.getDeclaredField("layout");
        assertTrue(Modifier.isPrivate(layout.getModifiers()));
    }

    @Test
    public void checkSecondLayoutVisibility() throws NoSuchFieldException {
        Field layout = SecondRoomMapLayout.class.getDeclaredField("layout");
        assertTrue(Modifier.isPrivate(layout.getModifiers()));
    }

    @Test
    public void checkThirdLayoutVisibility() throws NoSuchFieldException {
        Field layout = ThirdRoomMapLayout.class.getDeclaredField("layout");
        assertTrue(Modifier.isPrivate(layout.getModifiers()));
    }
}
