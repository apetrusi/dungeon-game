package com.example.myapplication.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.EntityMovement;
import com.example.myapplication.Model.HealthPowerUp;
import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.PlayerMovement;
import com.example.myapplication.Model.PowerUpEffect;
import com.example.myapplication.Model.ScorePowerUp;
import com.example.myapplication.Model.ThirdRoomMapLayout;
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

public class ThirdRoomActivity extends AppCompatActivity implements InputObserver {

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
    private int playerScore;
    private RelativeLayout gameLayout;
    private TileMap tilemap;
    private GameView gameView;
    private ThirdRoomMapLayout mapLayout;
    private GridView gridView;
    private TileSet tileSet;
    private PlayerView playerView;
    private EnemyView girlZombieView;
    private EnemyView skeletonView;
    private EnemyView girlZombie3View;
    private int specificSpriteResourceId;
    private Player player = Player.getInstance();
    private MyInputObserver inputObserver;
    private PowerUpView powerUpView;
    private boolean powerUpCollected = false;
    private PowerUpEffect powerUpEffect;
    private GameViewModel gameViewModel;
    private WeaponView weaponView;
    private Enemy girlZombie;
    private Enemy skeleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //-------------------------------INITIALISE SCREEN-------------------------------
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_room_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        gameLayout = findViewById(R.id.gameLayout);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;

        //-------------------------------INITIALISE ROOM-------------------------------
        // Initializing Tilemap
        mapLayout = new ThirdRoomMapLayout();

        // Retrieve columns and rows from MapLayout
        int columns = mapLayout.getColumns();
        int rows = mapLayout.getRows();

        // Initializing Tileset
        tileSet = new TileSet(this, R.drawable.floor_tile,
                R.drawable.wall_tile, R.drawable.exit_tile, 30, columns, rows);

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

        health = getIntent().getIntExtra("health", 100);
        player.setHealth(health);
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

        playerScore = getIntent().getIntExtra("playerScore", -1);

        String currentDateAndTime = getIntent().getStringExtra("currentDateTime");
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        gameViewModel.setInitialScore(playerScore);
        gameViewModel.getScore().observe(this, score -> {
            playerScore = score;
            player.setScore(playerScore);
            scoreView.setText("Score: " + playerScore);
        });
        gameViewModel.getGameOver().observe(this, gameOver -> {
            if (gameOver) {
                Intent endScreen = new Intent(this, EndingActivity.class);
                endScreen.putExtra("currentDateTime", currentDateAndTime);
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
        specificSpriteResourceId = getIntent().getIntExtra("spriteId", 1);
        EntityMovement playerMovement = new PlayerMovement();
        ((PlayerMovement) playerMovement).setMapLayout(mapLayout);
        player.setEntityMovement(playerMovement);
        player.setPositionX(10 * 30);
        player.setPositionY(10 * 30);
        player.setSpeed(getIntent().getIntExtra("speed", 30));
        playerView = new PlayerView(this, (float) player.getPositionX(),
                (float) player.getPositionY(),
                0.1f, specificSpriteResourceId);
        ((PlayerMovement) playerMovement).setPlayer(player);
        gameLayout.addView(playerView);
        // Add weapon view
        weaponView = new WeaponView(this, (float) player.getPositionX(), (float) player.getPositionY(), R.drawable.arrow_right);
        gameLayout.addView(weaponView);

        //-----------------------------------ADD ENEMIES------------------------------------
        girlZombie = EnemyFactory.createEnemy("GirlZombie", mapLayout);
        Player.getInstance().addObserver(girlZombie);
        girlZombie.setPositionX(600);
        girlZombie.setPositionY(300);
        girlZombieView = new EnemyView(this, (float) girlZombie.getPositionX(), (float) girlZombie.getPositionY(), girlZombie.getScale(), girlZombie.getSpriteResourceId());
        gameLayout.addView(girlZombieView);

        skeleton = EnemyFactory.createEnemy("Skeleton", mapLayout);
        Player.getInstance().addObserver(skeleton);
        skeleton.setPositionX(850);
        skeleton.setPositionY(770);
        skeletonView = new EnemyView(this, (float) skeleton.getPositionX(), (float) skeleton.getPositionY(), skeleton.getScale(), skeleton.getSpriteResourceId());
        gameLayout.addView(skeletonView);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    girlZombie.move();
                    girlZombieView.updatePosition(girlZombie.getPositionX(), girlZombie.getPositionY());
                    skeleton.move();
                    skeletonView.updatePosition(skeleton.getPositionX(), skeleton.getPositionY());
                });
            }
        }, 0, 450);
        //----------------------------ADD PowerUps------------------------------------
        powerUpView = new PowerUpView(this, 200, 350,
                0.1f, R.drawable.scorepowerup);
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
                weaponView.updatePosition((float) (player.getPositionX() - 90), (float) player.getPositionY() + 8, R.drawable.arrow_left);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                player.setFacingDirection(3);
                player.moveRight();
                weaponView.setDirection(3);
                weaponView.updatePosition((float) (player.getPositionX() + 30), (float) player.getPositionY() + 8, R.drawable.arrow_right);
                break;
            case KeyEvent.KEYCODE_DPAD_UP:
                player.setFacingDirection(0);
                player.moveUp();
                weaponView.setDirection(0);
                weaponView.updatePosition((float) (player.getPositionX() + 8), (float) (player.getPositionY() - 90), R.drawable.arrow_up);
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN:
                player.setFacingDirection(1);
                player.moveDown();
                weaponView.setDirection(1);
                weaponView.updatePosition((float) (player.getPositionX() + 8), (float) (player.getPositionY() + 30), R.drawable.arrow_down);
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
        if (player.getPositionX() < 240 && player.getPositionX() > 180
                && player.getPositionY() < 360 && player.getPositionY() > 270
                && !powerUpCollected) {
            powerUpCollected = true;
            powerUpEffect = new ScorePowerUp(player);
            gameViewModel.setScore(powerUpEffect.scoreEffect());
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
            Intent endingIntent = new Intent(this, EndingActivity.class);
            endingIntent.putExtra("currentDateTime", getCurrentDateTime());
            endingIntent.putExtra("playerName", playerName);
            endingIntent.putExtra("playerScore", playerScore);
            endingIntent.putExtra("spriteId", specificSpriteResourceId);
            startActivity(endingIntent);
            girlZombie.destroy();
            skeleton.destroy();
            finish();
        }
        // Update the player's view based on playerMovement
        playerView.updatePosition((float) player.getPositionX(), (float) player.getPositionY());
        healthView.setText("Health: " + player.getHealth());
    }
}
