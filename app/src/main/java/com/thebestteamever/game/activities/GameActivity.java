package com.thebestteamever.game.activities;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thebestteamever.game.Level;
import com.thebestteamever.game.R;
import com.thebestteamever.game.ServiceAPI.QuestionServiceHelper;

import java.util.Random;

public class GameActivity extends AppCompatActivity implements QuestionServiceHelper.QUESTIONResultListener {
    private ProgressBar progressBar;
    private ImageView leftImage;
    private ImageView rightImage;
    private TextView personName;
    private Level level;
    private int requestId;
    private boolean isLeft = true;
    private int count = 0;
    private final int MAX_LEVEL = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        leftImage = (ImageView) findViewById(R.id.imageView2);
        rightImage = (ImageView) findViewById(R.id.imageView3);
        personName = (TextView) findViewById(R.id.textView6);

        assert progressBar != null;
        progressBar.setVisibility(View.INVISIBLE);

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
    public void onQUESTIONResult(boolean success, Level result) {

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

        requestId = QuestionServiceHelper.makeQUESTION(this, "KEK", this);
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

    }

    public void wrongAnswer() {
        // TODO: Красный цвет, вибрация


        long mills = 100L;
        Vibrator vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
        vibrator.vibrate(mills);
    }

    public void finishGame() {
        finish();
    }


}
