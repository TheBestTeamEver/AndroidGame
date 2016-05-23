package com.thebestteamever.game.activities;

import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thebestteamever.game.Level;
import com.thebestteamever.game.R;
import com.thebestteamever.game.ServiceAPI.ServiceHelper;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements ServiceHelper.LevelResultListener {
    public static final String EXTRA_MESSAGE = "SCORE";
    private ProgressBar progressBar;
    private ImageView leftImage;
    private ImageView rightImage;
    private TextView personName;
    private Level level;
    private int requestId;
    private boolean isLeft = true;
    private int count = 0;
    private static final int MAX_LEVEL = 10;
    private int score = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        leftImage = (ImageView) findViewById(R.id.imageView2);
        rightImage = (ImageView) findViewById(R.id.imageView3);
        personName = (TextView) findViewById(R.id.textView6);

        startGame();
    }


    public void onLeftClick(View view) {
        checkAnswer(true);
        getNextLevel();
    }


    public void onRightClick(View view) {
        checkAnswer(false);
        getNextLevel();
    }

    @Override
    public void onLevelResult(boolean success, Level result) {

        progressBar.setVisibility(View.INVISIBLE);
        if (success && result != null) {
            this.level = result;
            refreshLevel();
        }

    }

    public void startGame() {
        // TODO: start game
        getNextLevel();
    }

    public void getNextLevel() {
        if (count == MAX_LEVEL) {
            finishGame();
        }

        requestId = ServiceHelper.makeLevel(this, "KEK", this);
        progressBar.setVisibility(View.VISIBLE);
        count++;
    }

    public void refreshLevel() {
        isLeft = new Random().nextBoolean();
        boolean isFirst = new Random().nextBoolean();
        assert (leftImage != null && rightImage != null);

        if (isFirst) {
            personName.setText(this.level.getFirstName());
        } else {
            personName.setText(this.level.getSecondName());
        }

        if (isLeft) {
            if (isFirst) {
                leftImage.setImageBitmap(this.level.getFirstImage());
                rightImage.setImageBitmap(this.level.getSecondImage());
            } else {
                leftImage.setImageBitmap(this.level.getSecondImage());
                rightImage.setImageBitmap(this.level.getFirstImage());
            }
        } else {
            if (isFirst) {
                leftImage.setImageBitmap(this.level.getSecondImage());
                rightImage.setImageBitmap(this.level.getFirstImage());
            } else {
                leftImage.setImageBitmap(this.level.getFirstImage());
                rightImage.setImageBitmap(this.level.getSecondImage());
            }
        }
    }

    public void checkAnswer(boolean isLeft) {
        if (this.isLeft == isLeft) {
            rightAnswer();
        } else {
            wrongAnswer();
        }
    }

    public void rightAnswer() {
        // TODO: Зеленый цвет
        score++;

    }

    public void wrongAnswer() {
        // TODO: Красный цвет, вибрация
        score--;

        long mills = 100L;
        Vibrator vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(mills);
    }

    public void finishGame() {
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(this.score));
        startActivity(intent);
        finish();
    }


}
