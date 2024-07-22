package com.example.myapplication.Model;

public class PlayerAttempt implements Comparable<PlayerAttempt> {
    private String playerName;
    private int score;
    private String dateTime;

    public PlayerAttempt(String playerName, int score, String dateTime) {
        this.playerName = playerName;
        this.score = score;
        this.dateTime = dateTime;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getScore() {
        return score;
    }
    public String getDateTime() {
        return dateTime;
    }
    @Override
    public int compareTo(PlayerAttempt other) {
        // Compare two different attempts.
        return Integer.compare(this.score, other.score);
    }
    @Override
    public String toString() {
        // Format attempt information as a string.
        return playerName + " - " + score + " points - " + dateTime;
    }
}
