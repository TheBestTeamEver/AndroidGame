package com.thebestteamever.game.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thebestteamever.game.R;
import com.thebestteamever.game.serviceapi.ServiceHelper;

public class PreActivity extends AppCompatActivity implements ServiceHelper.TopResultListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        int requestId = ServiceHelper.makeTop(this, "top", this);
    }

    @Override
    public void onTopResult(boolean success, String result) {
        startActivity(new Intent(PreActivity.this, LoginActivity.class));
        finish();
    }
}
