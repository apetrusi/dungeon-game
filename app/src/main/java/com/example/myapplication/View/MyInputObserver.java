package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;

public class MyInputObserver implements InputObserver {
    private AppCompatActivity activity;

    public MyInputObserver(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onInputReceived(int keyCode) {
        if (activity instanceof FirstRoomActivity) {
            ((FirstRoomActivity) activity).onInputReceived(keyCode);
        } else if (activity instanceof SecondRoomActivity) {
            ((SecondRoomActivity) activity).onInputReceived(keyCode);
        } else if (activity instanceof  ThirdRoomActivity) {
            ((ThirdRoomActivity) activity).onInputReceived(keyCode);
        }
    }
}