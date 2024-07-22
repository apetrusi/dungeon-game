package com.example.myapplication.Model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LeaderboardTest {
    private Leaderboard leaderboard;
    @Before
    public void setUp() {
        leaderboard = Leaderboard.getInstance();
        leaderboard.reset();
    }

    @Test
    public void testLeaderboardDescendingOrder() {
        // Add player attempts with scores in ascending order
        leaderboard.addAttempt(new PlayerAttempt("Player1", 100, "2023-10-10 10:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player2", 200, "2023-10-10 11:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player3", 300, "2023-10-10 12:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player4", 400, "2023-10-10 13:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player5", 500, "2023-10-10 14:00:00"));

        // Retrieve player attempts from the leaderboard
        List<PlayerAttempt> playerAttempts = leaderboard.getPlayerAttempts();

        // Check if the scores are in descending order
        for (int i = 0; i < playerAttempts.size() - 1; i++) {
            assertTrue(playerAttempts.get(i).getScore() >= playerAttempts.get(i + 1).getScore());
        }
    }
    @Test
    public void testTopFiveAttemptsDisplayed() {
        // Add six player attempts with scores in descending order
        leaderboard.addAttempt(new PlayerAttempt("Player1", 500, "2023-10-10 10:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player2", 400, "2023-10-10 11:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player3", 300, "2023-10-10 12:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player4", 200, "2023-10-10 13:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player5", 100, "2023-10-10 14:00:00"));
        leaderboard.addAttempt(new PlayerAttempt("Player6", 50, "2023-10-10 15:00:00"));

        // Retrieve player attempts from the leaderboard
        List<PlayerAttempt> playerAttempts = leaderboard.getPlayerAttempts();

        // Assert that only the top five attempts are displayed
        assertEquals(5, playerAttempts.size());

        // Assert that the top five attempts are the expected ones
        assertEquals(500, playerAttempts.get(0).getScore());
        assertEquals(400, playerAttempts.get(1).getScore());
        assertEquals(300, playerAttempts.get(2).getScore());
        assertEquals(200, playerAttempts.get(3).getScore());
        assertEquals(100, playerAttempts.get(4).getScore());
    }
}