package com.example.myapplication.ViewModel;
import androidx.lifecycle.ViewModel;
import android.os.CountDownTimer;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.Model.Enemy;

public class GameViewModel extends ViewModel {
    private String playerName;
    private String sprite;
    private int lives;
    private MutableLiveData<Integer> score;
    private MutableLiveData<Boolean> gameOver = new MutableLiveData<>(false);
    public GameViewModel() {
        score = new MutableLiveData<>(0);
    }
    public LiveData<Integer> getScore() {
        return score;
    }

    public void setScore(int newScore) {
        score.setValue(newScore);
    }

    public LiveData<Boolean> getGameOver() {
        return gameOver;
    }

    public void setInitialScore(int initialScore) {
        score.setValue(initialScore);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

}
