package com.example.myapplication.ViewModel;

import android.content.Context;
import android.graphics.Canvas;

import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.TileMap;
import com.example.myapplication.View.MyInputObserver;

// Observer interface for handling user inputs
/**
 * Responsible for creating and rendering the game view.
 * */
public class GameView extends SurfaceView implements Runnable {

    private MyInputObserver inputObserver;
    private boolean isPlaying;
    private Thread gameThread;
    private SurfaceHolder surfaceHolder;
    private TileMap tileMap;
    private final Player player;

    public GameView(Context context, TileMap tileMap) {
        super(context);
        surfaceHolder = getHolder();
        this.tileMap = tileMap;
        player = new Player("Player", 100, 5, 5, 1, 2000, 100);
        setFocusable(true);
    }

    public void registerInputObserver(MyInputObserver observer) {
        this.inputObserver = observer;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (inputObserver != null) {
            inputObserver.onInputReceived(keyCode);
        }
        return true; // or return super.onKeyDown(keyCode, event);
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
        }
    }

    public void update() {
        // Update game logic here
        player.update();
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            tileMap.draw(canvas); // Draw the tile map
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void resume() {
        isPlaying = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        isPlaying = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}