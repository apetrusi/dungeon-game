package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import com.example.myapplication.R;


/**
 * Welcome Screen
 * */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button gameStartBtn = findViewById(R.id.startButton);
        gameStartBtn.setOnClickListener(v -> {
            //Open ConfigScreen
            Intent start = new Intent(MainActivity.this, ConfigurationActivity.class);
            startActivity(start);
            finish();
        });

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            //Exit out of game
            finishAffinity();
        });

    }
}

