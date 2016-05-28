package com.thebestteamever.game.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.thebestteamever.game.R;
import com.thebestteamever.game.serviceapi.ServiceHelper;
import com.thebestteamever.game.serviceapi.parcelable.LoginParams;
import com.thebestteamever.game.serviceapi.parcelable.Score;

public class GameOverActivity extends AppCompatActivity implements ServiceHelper.ScoreResultListener {

    private int requestId;
    private String score;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        score = intent.getStringExtra(GameActivity.EXTRA_MESSAGE);

        showScore();
        sendScoreToServer();
    }


    private void showScore() {

        TextView textView = (TextView) findViewById(R.id.textView7);
        assert textView != null;
        textView.setText(score);
    }

    private void sendScoreToServer() {
        sharedPreferences = getSharedPreferences(LoginActivity.SAVED, Context.MODE_PRIVATE);

        if (sharedPreferences.contains(LoginActivity.SAVED_LOGIN)) {

            String login = sharedPreferences.getString(LoginActivity.SAVED_LOGIN, "");
            requestId = ServiceHelper.makeScore(this, new Score(login, score), this);
        }
    }

    @Override
    public void onScoreResult(boolean success, String result) {

    }
}
