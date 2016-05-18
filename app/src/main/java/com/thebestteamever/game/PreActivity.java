package com.thebestteamever.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre);

        Thread splash_time = new Thread() {
            public void run() {
                try {
                    sleep(2000); //общение с серваком в будущем
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(PreActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        splash_time.start();
    }
}
