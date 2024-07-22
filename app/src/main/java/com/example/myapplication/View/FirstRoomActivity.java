package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.EntityMovement;
import com.example.myapplication.Model.FirstRoomMapLayout;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.PlayerMovement;
import com.example.myapplication.Model.PowerUpEffect;
import com.example.myapplication.Model.SpeedPowerUp;
import com.example.myapplication.Model.TileMap;
import com.example.myapplication.Model.TileSet;
import com.example.myapplication.ViewModel.EnemyFactory;
import com.example.myapplication.ViewModel.EnemyView;
import com.example.myapplication.ViewModel.GameView;
import com.example.myapplication.ViewModel.GameViewModel;
import com.example.myapplication.R;
import com.example.myapplication.ViewModel.MapAdapter;
import com.example.myapplication.ViewModel.PlayerView;
import com.example.myapplication.ViewModel.PowerUpView;
import com.example.myapplication.ViewModel.WeaponView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FirstRoomActivity extends AppCompatActivity implements InputObserver {

    private int difficulty;
    private String playerName;
    private String sprite;
    private TextView playerNameView;
    private TextView spriteNameView;
    private TextView healthView;
    private TextView difficultyView;
    private TextView scoreView;
    private int screenWidth;
    private int screenHeight;
    private int health;
    private static boolean zeroHealth;
    private int playerScore;
    private RelativeLayout gameLayout;
    private TileMap tilemap;
    private GameView gameView;
    private FirstRoomMapLayout mapLayout;
    private GridView gridView;
    private TileSet tileSet;
    private PlayerView playerView;
    private GameViewModel gameViewModel;
    private EnemyView enemy1View;
    private EnemyView skeletonView;
    private int playerX;
    private int playerY;
    private int specificSpriteResourceId;
    private Player player = Player.getInstance();
    private MyInputObserver inputObserver;
    private Handler enemyMovementHandler;
    private static final int ENEMY_MOVEMENT_INTERVAL = 1000;
    private PowerUpView powerUpView;
    private boolean powerUpCollected = false;
    private PowerUpEffect powerUpEffect;
    private WeaponView weaponView;
    private Enemy enemy1;
    private Enemy skeleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //-------------------------------INITIALISE SCREEN-------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_room_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        gameLayout = findViewById(R.id.gameLayout);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        //-------------------------------INITIALISE ROOM-------------------------------
        // Initializing Tilemap
        mapLayout = new FirstRoomMapLayout();
        // Retrieve columns and rows from MapLayout
        int columns = mapLayout.getColumns();
        int rows = mapLayout.getRows();
        // Initializing Tileset
        tileSet = new TileSet(this, R.drawable.floor_tile, R.drawable.wall_tile,
                R.drawable.exit_tile, 30, columns, rows);
        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new MapAdapter(this, mapLayout.getLayout()));
        tilemap = new TileMap(mapLayout.getLayout(), tileSet);
        gameView = new GameView(this, tilemap);
        // Set the dimensions of the GameView
        gameView.setLayoutParams(new RelativeLayout.LayoutParams(columns * 30, rows * 30));
        // Add the GameView to the gameLayout
        gameLayout.addView(gameView);
        inputObserver = new MyInputObserver(this);
        gameView.registerInputObserver(inputObserver);

        //---------------------------------GET USER INPUT---------------------------------
        playerNameView = findViewById(R.id.playerNameTextView);
        spriteNameView = findViewById(R.id.spriteTextView);
        healthView = findViewById(R.id.livesTextView);
        difficultyView = findViewById(R.id.difficultyTextView);
        scoreView = findViewById(R.id.scoreTextView);
        health = 100;
        // Get difficulty selected from Config screen.
        difficulty = getIntent().getIntExtra("difficulty", 2);
        // Get playerName from Config Screen.
        playerName = getIntent().getStringExtra("playerName");
        // Get SpriteName from Config Screen.
        sprite = getIntent().getStringExtra("spriteName");
        switch (difficulty) {
        case 1:
            difficultyView.setText("Difficulty: Easy");
            break;
        case 2:
            difficultyView.setText("Difficulty: Medium");
            break;
        case 3:
            difficultyView.setText("Difficulty: Hard");
            break;
        default:
        }
        playerNameView.setText("Name: " + playerName);
        spriteNameView.setText("Sprite: " + sprite);
        healthView.setText("Health: " + health);
        player.setName(playerName);
        player.setPositionX(screenWidth / 2);
        player.setPositionY(screenHeight / 2);
        player.setHealth(health);
        player.setDifficulty(difficulty);
        player.setSpeed(20);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.getScore().observe(this, score -> {
            playerScore = score;
            player.setScore(playerScore);
            scoreView.setText("Score: " + playerScore);
        });
        gameViewModel.getGameOver().observe(this, gameOver -> {
            if (gameOver) {
                Intent endScreen = new Intent(this, EndingActivity.class);
                endScreen.putExtra("currentDateTime", getCurrentDateTime());
                endScreen.putExtra("playerName", playerName);
                endScreen.putExtra("playerScore", playerScore);
                startActivity(endScreen);
                finish();
            }
        });
        gameViewModel.setPlayerName(playerName);
        gameViewModel.setSprite(sprite);
        gameViewModel.setLives(health);

        //-----------------------------------ADD PLAYER------------------------------------
        switch (sprite) {
        case "Sprite1":
            specificSpriteResourceId = R.drawable.playerone;
            break;
        case "Sprite2":
            specificSpriteResourceId = R.drawable.player2;
            break;
        case "Sprite3":
            specificSpriteResourceId = R.drawable.player3;
            break;
        default:
            specificSpriteResourceId = R.drawable.playerone;
        }
        EntityMovement playerMovement = new PlayerMovement();
        ((PlayerMovement) playerMovement).setMapLayout(mapLayout);
        player.setEntityMovement(playerMovement);
        player.setPositionX(25 * 30);
        player.setPositionY(20 * 30);
        player.setSpeed(10);
        playerView = new PlayerView(this, (float) player.getPositionX(),
                (float) player.getPositionY(), 0.1f, specificSpriteResourceId);
        ((PlayerMovement) playerMovement).setPlayer(player);
        gameLayout.addView(playerView);
        // Add weapon view
        weaponView = new WeaponView(this, (float) player.getPositionX(),
                (float) player.getPositionY(), R.drawable.arrow_right);
        gameLayout.addView(weaponView);

        //----------------------------ADD ENEMIES------------------------------------
        enemy1 = EnemyFactory.createEnemy("Zombie", mapLayout);
        Player.getInstance().addObserver(enemy1);
        enemy1.setPositionX(900);
        enemy1.setPositionY(600);
        enemy1View = new EnemyView(this, (float) enemy1.getPositionX(),
                (float) enemy1.getPositionY(), enemy1.getScale(), enemy1.getSpriteResourceId());
        gameLayout.addView(enemy1View);
        skeleton = EnemyFactory.createEnemy("Skeleton", mapLayout);
        Player.getInstance().addObserver(skeleton);
        skeleton.setPositionX(1000);
        skeleton.setPositionY(850);
        skeletonView = new EnemyView(this, (float) skeleton.getPositionX(),
                (float) skeleton.getPositionY(),
                skeleton.getScale(), skeleton.getSpriteResourceId());
        skeleton.setEnemyView(skeletonView);
        gameLayout.addView(skeletonView);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    enemy1.move();
                    enemy1View.updatePosition(enemy1.getPositionX(), enemy1.getPositionY());
                    skeleton.move();
                    skeletonView.updatePosition(skeleton.getPositionX(), skeleton.getPositionY());
                });
            }
        }, 0, 750);

        //----------------------------ADD PowerUps------------------------------------
        powerUpView = new PowerUpView(this, 1000, 700,
                0.08f, R.drawable.speedpotion);
        gameLayout.addView(powerUpView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());
        return currentDateAndTime;
    }

    @Override
    public void onInputReceived(int keyCode) {
        switch (keyCode) {
        case KeyEvent.KEYCODE_DPAD_LEFT:
            player.setFacingDirection(2);
            player.moveLeft();
            weaponView.setDirection(2);
            weaponView.updatePosition((float) (player.getPositionX() - 90),
                    (float) player.getPositionY() + 8, R.drawable.arrow_left);
            break;
        case KeyEvent.KEYCODE_DPAD_RIGHT:
            player.setFacingDirection(3);
            player.moveRight();
            weaponView.setDirection(3);
            weaponView.updatePosition((float) (player.getPositionX() + 30),
                    (float) player.getPositionY() + 8, R.drawable.arrow_right);
            break;
        case KeyEvent.KEYCODE_DPAD_UP:
            player.setFacingDirection(0);
            player.moveUp();
            weaponView.setDirection(0);
            weaponView.updatePosition((float) (player.getPositionX() + 8),
                    (float) (player.getPositionY() - 90), R.drawable.arrow_up);
            break;
        case KeyEvent.KEYCODE_DPAD_DOWN:
            player.setFacingDirection(1);
            player.moveDown();
            weaponView.setDirection(1);
            weaponView.updatePosition((float) (player.getPositionX() + 8),
                    (float) (player.getPositionY() + 30), R.drawable.arrow_down);
            break;
        case KeyEvent.KEYCODE_SPACE: // Attack
            player.attack(weaponView);
            break;
        default:
        }

        // Check if player's score has changed
        if (player.getScore() != playerScore) {
            playerScore = player.getScore();
            gameViewModel.setScore(playerScore);
        }
        // Check if player has collided with powerup
        if (player.getPositionX() < 1050 && player.getPositionX() > 930
                && player.getPositionY() < 750 && player.getPositionY() > 630
                && !powerUpCollected) {
            powerUpCollected = true;

            powerUpEffect = new SpeedPowerUp(player);
            player.setSpeed(powerUpEffect.speedEffect());
            gameLayout.removeView(powerUpView);
        }

        // Check if the player health has decreased below zero
        if (player.getHealth() <= 0) {
            Intent gameOverIntent = new Intent(this, GameOverActivity.class);
            startActivity(gameOverIntent);
            finish();
        }

        // Check if the player has reached the exit tile
        int playerRow = (int) Math.floor(player.getPositionY() / tilemap.getTileSize());
        int playerColumn = (int) Math.floor(player.getPositionX() / tilemap.getTileSize());
        if (mapLayout.isExit(playerRow, playerColumn)) {
            // Transition to the second room
            Intent secondRoomIntent = new Intent(this, SecondRoomActivity.class);
            secondRoomIntent.putExtra("currentDateTime", getCurrentDateTime());
            secondRoomIntent.putExtra("playerName", playerName);
            secondRoomIntent.putExtra("playerScore", playerScore);
            secondRoomIntent.putExtra("spriteId", specificSpriteResourceId);
            secondRoomIntent.putExtra("spriteName", sprite);
            secondRoomIntent.putExtra("health", player.getHealth());
            secondRoomIntent.putExtra("difficulty", difficulty);
            secondRoomIntent.putExtra("speed", player.getSpeed());
            startActivity(secondRoomIntent);
            enemy1.destroy();
            skeleton.destroy();
            finish();
        }
        // Update the player's view based on playerMovement
        playerView.updatePosition((float) player.getPositionX(), (float) player.getPositionY());
        // Update the player's health
        healthView.setText("Health: " + player.getHealth());
    }
    public static boolean moveToGameOver(int playerHealth) {
        zeroHealth = false;
        if (playerHealth <= 0) {
            zeroHealth = true;
        }
        return zeroHealth;
    }
    public static int scoreUpdate(Enemy enemy, int score) {
        return (enemy.getScoreValue() + score);
    }
}