package com.thebestteamever.game.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.thebestteamever.game.R;
import com.thebestteamever.game.serviceapi.ServiceHelper;

public class RegistrationActivity extends AppCompatActivity implements ServiceHelper.RegistrationResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    @Override
    public void onRegistrationResult(boolean success, String result) {
        switch (result) {
            case "Ok":
                // TODO
                break;
            case "Login is already used":
                // TODO
                break;
            case "No some fields":
                // TODO
                break;
            default:
                // TODO
                break;
        }
    }
}
