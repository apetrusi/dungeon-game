package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.myapplication.Model.Player;
import com.example.myapplication.R;

/**
 * Configuration Screen.
 *
 * Used to enter a player name, choose sprite, and game difficulty.
 * */
public class ConfigurationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Get and set name
        EditText textInput = findViewById(R.id.playerNameTextView);
        Button startBtn = findViewById(R.id.buttonStart);
        startBtn.setOnClickListener(v -> {
            Player player = Player.getInstance();

            String name = textInput.getText().toString();
            if (player.isUsernameValid(name)) {
                player.setName(name);
            } else {
                textInput.setError("Invalid name");
            }

            //Get and set sprite
            String spriteName;
            RadioGroup spriteRadioGroup = findViewById(R.id.radioGroupSprite);
            int checkedRadioButtonId = spriteRadioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.radioButtonSprite1) {
                spriteName = "Sprite1";
            } else if (checkedRadioButtonId == R.id.radioButtonSprite2) {
                spriteName = "Sprite2";
            } else if (checkedRadioButtonId == R.id.radioButtonSprite3) {
                spriteName = "Sprite3";
            } else {
                spriteName = "Sprite1";
            }

            //Get difficulty
            RadioGroup difficultyRadioGroup = findViewById(R.id.radioGroupDifficulty);
            int difficulty = 2;
            checkedRadioButtonId = difficultyRadioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId == R.id.radioButtonDiffEasy) {
                difficulty = 1;
            } else if (checkedRadioButtonId == R.id.radioButtonDiffMed) {
                difficulty = 2;
            } else if (checkedRadioButtonId == R.id.radioButtonDiffHard) {
                difficulty = 3;
            } else {
                difficulty = 2;
            }

            //Open GameScreen
            Intent game = new Intent(ConfigurationActivity.this, FirstRoomActivity.class);
            game.putExtra("difficulty", difficulty);
            game.putExtra("spriteName", spriteName);
            game.putExtra("playerName", name);
            startActivity(game);
            finish();
        });

        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && !editable.toString().isEmpty()
                        && !editable.toString().trim().isEmpty()) {
                    startBtn.setEnabled(true); // Enable the button when the input is valid
                } else {
                    startBtn.setEnabled(false); // Disable the button when the input is invalid
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
    }

}
