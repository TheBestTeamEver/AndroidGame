package com.thebestteamever.game.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.thebestteamever.game.R;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        showScore();
        sendScoreToServer();
    }


    private void showScore() {
        Intent intent = getIntent();
        String message = intent.getStringExtra(GameActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView7);
        assert textView != null;
        textView.setText(message);
    }

    private void sendScoreToServer() {
        // TODO: send score to server
    }
}
