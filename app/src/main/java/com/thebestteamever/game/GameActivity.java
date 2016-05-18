package com.thebestteamever.game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.thebestteamever.game.ServiceAPI.QuestionServiceHelper;

public class GameActivity extends AppCompatActivity implements QuestionServiceHelper.QUESTIONResultListener {
    private ProgressBar progressBar;
    private int requestId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        assert progressBar != null;
        progressBar.setVisibility(View.INVISIBLE);

    }

    public void onLeftClick(View view) {
        requestId = QuestionServiceHelper.makeQUESTION(this, "KEK", this);
        progressBar.setVisibility(View.VISIBLE);

    }

    public void onRightClick(View view) {
        requestId = QuestionServiceHelper.makeQUESTION(this, "KEK", this);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void onQUESTIONResult(boolean success, QuestionSet result) {
        progressBar.setVisibility(View.INVISIBLE);
        if (success) {
            ImageView mImg;
            ImageView mImg2;
            mImg = (ImageView) findViewById(R.id.imageView2);
            mImg2 = (ImageView) findViewById(R.id.imageView3);
            assert (mImg != null && mImg2 != null);
            mImg.setImageBitmap(result.getFirstImage());
            mImg2.setImageBitmap(result.getsecondImage());
//        } else {
//            mResultTextView.setText(String.format("FAIL: %s", result));
        }

    }
}
