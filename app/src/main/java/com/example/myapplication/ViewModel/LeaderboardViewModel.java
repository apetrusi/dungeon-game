package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.Leaderboard;
import com.example.myapplication.Model.PlayerAttempt;
import java.util.List;

public class LeaderboardViewModel {
    private Leaderboard leaderboard;
    public LeaderboardViewModel() {
        leaderboard = Leaderboard.getInstance();
    }
    public String getFormattedLeaderboard() {
        List<PlayerAttempt> playerAttemptList = Leaderboard.getInstance().getPlayerAttempts();
        StringBuilder leaderboardText = new StringBuilder();
        // Create the header for leaderboard
        leaderboardText.append("Rank - Player - Score - Date/Time\n");
        int count = 1;
        // Add each player attempt to the visual display
        for (PlayerAttempt attempt : playerAttemptList) {
            leaderboardText.append(count).append(" - ").append(attempt.getPlayerName())
                    .append(" - ");
            leaderboardText.append(attempt.getScore()).append(" points - ")
                    .append(attempt.getDateTime()).append("\n");
            count++;
        }
        return leaderboardText.toString();
    }
}
