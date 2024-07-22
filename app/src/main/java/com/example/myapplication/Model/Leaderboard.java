package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Leaderboard {
    private static Leaderboard instance;
    private List<PlayerAttempt> playerAttempts;
    private static final int MAX_SIZE = 5;

    private Leaderboard() {
        playerAttempts = new ArrayList<>();
    }
    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }
    public void addAttempt(PlayerAttempt attempt) {
        playerAttempts.add(attempt);
        // Sort leaderboard in descending order based on scores.
        Collections.sort(playerAttempts, Collections.reverseOrder());
        // Only display the top five scores.
        if (playerAttempts.size() > MAX_SIZE) {
            playerAttempts = playerAttempts.subList(0, MAX_SIZE);
        }
    }
    public List<PlayerAttempt> getPlayerAttempts() {
        return playerAttempts;
    }
    public void reset() {
        // Clears the log of player attempts
        playerAttempts.clear();
    }
}
