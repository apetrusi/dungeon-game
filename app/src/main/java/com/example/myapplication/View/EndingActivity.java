package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import com.example.myapplication.Model.Leaderboard;
import com.example.myapplication.Model.PlayerAttempt;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.LeaderboardViewModel;


public class EndingActivity extends AppCompatActivity {
    private int playerScore;
    private String playerName;
    private String currentDateTime;
    private LeaderboardViewModel leaderboardViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ending);

        Button exitBtn = findViewById(R.id.buttonExit);
        exitBtn.setOnClickListener(v -> {
            finishAffinity();
        });

        Button restartBtn = findViewById(R.id.buttonRestart);
        restartBtn.setOnClickListener(v -> {
            Intent intent = new Intent(EndingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        playerScore = getIntent().getIntExtra("playerScore", -100);
        playerName = getIntent().getStringExtra("playerName");
        currentDateTime = getIntent().getStringExtra("currentDateTime");
        PlayerAttempt playerAttempt = new PlayerAttempt(playerName, playerScore, currentDateTime);
        Leaderboard.getInstance().addAttempt(playerAttempt);

        TextView scoreTextView = findViewById(R.id.scoreTextView);

        scoreTextView.setText("Score: " + playerScore);

        leaderboardViewModel = new LeaderboardViewModel();
        TextView leaderboardTextView = findViewById(R.id.leaderboardTextView);
        String leaderboardText = leaderboardViewModel.getFormattedLeaderboard();
        leaderboardTextView.setText(leaderboardText);
    }
}
